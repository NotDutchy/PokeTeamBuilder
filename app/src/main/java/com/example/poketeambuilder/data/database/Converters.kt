package com.example.poketeambuilder.data.database

import androidx.room.TypeConverter
import com.example.poketeambuilder.domain.Extra
import com.example.poketeambuilder.domain.Move
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromStringToMoveList(value: String): List<Move> {
        var gson = Gson();
        return gson.fromJson(value, Array<Move>::class.java).toList()
    }

    @TypeConverter
    fun fromList(value: List<Move>): String {
        var gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringToMove(value: String): Move {
        var gson = Gson();
        return gson.fromJson(value, Move::class.java)
    }

    @TypeConverter
    fun fromList(value: Move): String {
        var gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromExtra(value: Extra): String {
        var gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringToExtra(value: String): Extra {
        var gson = Gson()
        return gson.fromJson(value, Extra::class.java)
    }
}