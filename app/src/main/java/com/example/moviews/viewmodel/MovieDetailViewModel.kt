package com.example.moviews.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import com.example.moviews.repository.MovieRepository
import com.example.moviews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    application: Application
) : BaseViewModel(application){

    suspend fun getMovie() : Resource<MovieResponse>{
        return repository.getMovieData()
    }


}