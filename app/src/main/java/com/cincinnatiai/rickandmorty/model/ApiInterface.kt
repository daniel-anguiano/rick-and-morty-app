package com.cincinnatiai.rickandmorty.model

import com.cincinnatiai.rickandmorty.data.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse
}
