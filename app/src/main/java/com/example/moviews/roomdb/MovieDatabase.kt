package com.example.moviews.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviews.model.DataConverter
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(MovieResponseItems::class), version = 1)
@TypeConverters(DataConverter::class)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun movieDao() : MovieDao


    //Singleton

    companion object{
        @Volatile private var instance : MovieDatabase? = null

        private val lock = Any()

        @OptIn(InternalCoroutinesApi::class)
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        //applicationContext kullanmanın amacı;  fragment yada activity nin contexti
        // detroy olması yada ekranın yan çevrilmesi gibi durumlarda app i çökermesin ve database ile işimi
        //engel olmasın diye applicationın context ini alıyoruz.

        //instance var ise instance donduruyor.
        //instance yok ise bu fonksiyon ile databsae i olusturur ve instance a esitler
        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, MovieDatabase::class.java, "moviedatabase"
        ).build()
    }
}