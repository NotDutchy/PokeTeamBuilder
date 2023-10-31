package com.example.poketeambuilder.data.database

import androidx.room.TypeConverter
import com.example.poketeambuilder.domain.Move
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromString(value: String): List<Move> {
        var gson = Gson();
        return gson.fromJson(value, Array<Move>::class.java).toList()
    }

    @TypeConverter
    fun fromList(value: List<Move>): String {
        var gson = Gson()
        return gson.toJson(value)
    }
}