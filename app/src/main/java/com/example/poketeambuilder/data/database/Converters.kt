package com.example.poketeambuilder.data.database

import androidx.room.TypeConverter
import com.example.poketeambuilder.data.model.MoveEntryApiModel
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromString(value: String): List<MoveEntryApiModel> {
        var gson = Gson();
        return gson.fromJson(value, Array<MoveEntryApiModel>::class.java).toList()
    }

    @TypeConverter
    fun fromList(value: List<MoveEntryApiModel>): String {
        var gson = Gson()
        return gson.toJson(value)
    }
}