package com.example.moviews.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import com.example.moviews.repository.MovieRepository
import com.example.moviews.roomdb.MovieDatabase
import com.example.moviews.util.Constrants.LOGO_URL
import com.example.moviews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository,
    application: Application
) : BaseViewModel(application) {

    //view sadece movielist degerini gorebilecek cunku viewmodel den movielist
    //gonderiyoruz.
    var movieList = mutableStateOf<List<MovieResponseItems>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)


    private lateinit var dataStore:DataStore<Preferences>





//SEARCH
    //her arama sonrasında butun verileri tekrar bastan yuklememek icin
    //ve her seferinde internetten verilei cekmek yuk olacagı icin
    //indirdigimiz liste icerisinden arama yaparaız.
    //bu indirdigimiz verileri temporary olarak inialMovieList icerisine kaydederiz
    //
    private var initalMovieList = listOf<MovieResponseItems>()
    private var isSearchStarting = true

    init {
        loadMovies()
    }

    fun searchMovieList(query : String){
        val listToSearch = if(isSearchStarting){
            movieList.value
        }else{
            initalMovieList
        }
        launch(Dispatchers.Default) {
            if(query.isEmpty()){
                movieList.value = initalMovieList
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.original_title.contains(query.trim(), ignoreCase = true)
            }

            //kaydetme islemi
            if(isSearchStarting){
                initalMovieList = movieList.value
                isSearchStarting = false
            }
            movieList.value = results
        }
    }




//data ları ındırme
    fun loadMovies(){
        launch {
            isLoading.value = true

            when(repository.getMovieData()){
                is Resource.Success -> {

                    val movieItems = repository.getMovieData().data!!.results.mapIndexed { index, item ->
                        MovieResponseItems(item.id,LOGO_URL+item.backdrop_path,item.original_title,item.overview,item.popularity,
                            LOGO_URL+item.poster_path, item.release_date, item.vote_average)
                    }

                    errorMessage.value = ""
                    isLoading.value = false
                    movieList.value += movieItems

                   // storeInSQLite(movieItems)
                }
                is Resource.Error -> {
                    errorMessage.value = repository.getMovieData().message!!
                    isLoading.value = false
                }
                else -> Unit
            }
        }
    }

  /* private fun showMovies(movieList : List<MovieResponseItems>){
        launch {
            val dao = MovieDatabase(getApplication()).movieDao()
            var movie = dao.getAllMovies()

        }
   }

    private fun storeInSQLite(movieList : List<MovieResponseItems>){
        launch {
            val dao = MovieDatabase(getApplication()).movieDao()
           // dao.deleteAllMovie()
            val listLong = dao.insertAll(*movieList.toTypedArray())
            var i = 0
            while (i< movieList.size){
                movieList[i].id= listLong[i].toString()
                i+=1
            }

        }
    }*/

}


