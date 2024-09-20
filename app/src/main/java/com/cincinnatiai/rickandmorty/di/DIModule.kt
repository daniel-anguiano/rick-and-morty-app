package com.cincinnatiai.rickandmorty.di

import com.cincinnatiai.rickandmorty.api.CharactersInterface
import com.cincinnatiai.rickandmorty.repository.CharactersRepository
import com.cincinnatiai.rickandmorty.repository.CharactersRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DIModule {

    private val baseUrl: String = "https://rickandmortyapi.com/api/"

    private val retrofit: Retrofit by lazy {
        return@lazy Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val api: CharactersInterface by lazy {
        retrofit.create(CharactersInterface::class.java)
    }

    val charactersRepository: CharactersRepository by lazy {
        CharactersRepositoryImpl(api)
    }
}