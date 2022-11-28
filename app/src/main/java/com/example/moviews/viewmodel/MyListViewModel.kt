package com.example.moviews.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import com.example.moviews.repository.MovieRepository
import com.example.moviews.roomdb.MovieDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val repository: MovieRepository,
    application: Application
) : BaseViewModel(application = application) {


    //val repo = repository.getMovieData()




}