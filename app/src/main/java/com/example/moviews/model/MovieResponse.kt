package com.example.moviews.model

import androidx.room.*


data class MovieResponse (
    val page: Int,
    val results: List<MovieResponseItems>

   // @Relation(parentColumn = "result", entityColumn = "", entity = MovieResponseItems.class)
)