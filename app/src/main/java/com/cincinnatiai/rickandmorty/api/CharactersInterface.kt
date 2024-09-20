package com.cincinnatiai.rickandmorty.api

import com.cincinnatiai.rickandmorty.data.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersInterface {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse
}
