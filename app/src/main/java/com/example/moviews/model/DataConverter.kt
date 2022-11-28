package com.example.moviews.model

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
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
