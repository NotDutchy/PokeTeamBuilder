package com.example.poketeambuilder.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.poketeambuilder.data.model.PokeEntity
import com.example.poketeambuilder.domain.Move
import com.example.poketeambuilder.domain.Pokemon

@Dao
interface PokeDao {
    @Query("SELECT * FROM pokeentity")
    fun getAll(): List<PokeEntity>

    @Query("DELETE FROM pokeentity")
    fun deleteAll();

    @Query("SELECT * FROM pokeentity WHERE id LIKE :id")
    fun getPokeById(id: Int): PokeEntity

    @Query("SELECT COUNT(poke_name) FROM pokeentity")
    fun getDatabaseSize(): Int

    @Query("SELECT moves FROM pokeentity WHERE id LIKE :id")
    fun getPokeMoves(id: Int): List<Move>

    @RawQuery
    fun getFilteredPokemons(query: SimpleSQLiteQuery): List<PokeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(poke: PokeEntity)
}