package com.example.moviews.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems

@Dao
interface MovieDao {
//Database Access Object

   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movies : MovieResponseItems) : List<MovieResponseItems>


    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies() : List<MovieResponseItems>

    @Query("SELECT * FROM movie_table WHERE id = :movieId")
    suspend fun getMovieForMyList(movieId: Int) : MovieResponseItems

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovie()
*/







}