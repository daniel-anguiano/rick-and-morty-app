package com.cincinnatiai.rickandmorty.repository

import com.cincinnatiai.rickandmorty.api.CharactersInterface
import com.cincinnatiai.rickandmorty.data.Character
import com.cincinnatiai.rickandmorty.data.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CharactersRepository {

    suspend fun fetchCharacters(page: Int = 0): CharacterResponse
}

interface CharactersRepositoryV2 {

    suspend fun fetchCharacter(characterId: Int): Character?
}

class CharactersRepositoryImpl (private val api: CharactersInterface) : CharactersRepository, CharactersRepositoryV2 {

    private val cachedCharacters: List<Character> = mutableListOf()

    override suspend fun fetchCharacters(page: Int): CharacterResponse = withContext(Dispatchers.IO) { api.getCharacters(page) }

    override suspend fun fetchCharacter(characterId: Int): Character? {
        return null
    }

}