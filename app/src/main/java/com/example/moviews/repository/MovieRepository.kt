package com.example.moviews.repository

import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import com.example.moviews.service.MovieApiService
import com.example.moviews.util.Constrants.API_KEY
import com.example.moviews.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieRepository @Inject constructor(
    private val api : MovieApiService
) {

    suspend fun getMovieData() : Resource<MovieResponse>{
        val response = try{
            api.getMovies(API_KEY)
        } catch (e:Exception){
            return Resource.Error("error")
        }
        return Resource.Success(response)
    }
    suspend fun getMovieLogos(movieLogo : MovieResponseItems) : Resource<MovieResponse>{
        val response = try{
            api.getMovieLogo()
        } catch (e:Exception){
            return Resource.Error("error")
        }
        return Resource.Success(response)
    }


}