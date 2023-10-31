package com.example.poketeambuilder.data.network

import com.example.poketeambuilder.data.model.PokeApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon/{id}")
    suspend fun GetPokemonFromApi(@Path("id") id: Int): Response<PokeApiModel>;
}