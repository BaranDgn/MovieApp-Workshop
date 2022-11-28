package com.example.moviews.service

import com.example.moviews.model.MovieResponse
import com.example.moviews.util.Constrants.LOGO_URL
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

//"https://api.themoviedb.org/3/movie/popular?api_key=081a2be23586091b5723cb623c6283af&language=en-US&page=1"

    @GET("popular?")
    suspend fun getMovies(@Query("api_key") api_key : String) : MovieResponse


    @GET(LOGO_URL)
    suspend fun getMovieLogo() : MovieResponse
}