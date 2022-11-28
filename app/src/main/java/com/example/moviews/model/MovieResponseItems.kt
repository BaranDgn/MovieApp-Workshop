package com.example.moviews.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_table")
data class MovieResponseItems(

  //  @ColumnInfo(name= "id")
    var id: String,

  //  @ColumnInfo(name= "backdrop_path")
    val backdrop_path: String,

    //@ColumnInfo(name = "original_title")
    val original_title: String,

    //@ColumnInfo(name = "overview")
    val overview: String,

    //@ColumnInfo(name = "popularity")
    val popularity: Double,

    //@ColumnInfo(name = "poster_path")
    val poster_path: String,

    //@ColumnInfo(name = "release_date")
    val release_date: String,

    //@ColumnInfo(name = "vote_average")
    val vote_average: Double,
){

    //model kendisi id yi otomatik arttıracak
    //constructor icine vermememizin nedeni her seferinde id istemesin diye
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}