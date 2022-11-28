package com.example.moviews.viewmodel

import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.TypeConverter
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataConverter {

    @TypeConverter
    fun fromListToString(list: List<*>): String {
        val type = object: TypeToken<List<*>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toData(dataString: String?): List<MovieResponseItems> {
        if(dataString == null || dataString.isEmpty()) {
            return mutableListOf()
        }
        val type: Type = object : TypeToken<List<MovieResponseItems>>() {}.type
        return Gson().fromJson(dataString, type)
    }
}