package com.example.poketeambuilder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.poketeambuilder.data.dao.PokeDao
import com.example.poketeambuilder.data.model.PokeEntity

@Database(entities = [PokeEntity::class], version = 0)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokeDao
}