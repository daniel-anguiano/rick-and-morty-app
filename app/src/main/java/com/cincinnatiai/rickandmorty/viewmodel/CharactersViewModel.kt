package com.cincinnatiai.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cincinnatiai.rickandmorty.data.Character
import com.cincinnatiai.rickandmorty.repository.CharactersRepository
import kotlinx.coroutines.launch

class CharactersViewModel(internal val repo: CharactersRepository) : ViewModel() {
    internal val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    @Volatile
    internal var page: Int = 1

    @Volatile
    internal var isAbleToLoadMore = true

    fun fetchCharacters() {
        if (!isAbleToLoadMore) return
        viewModelScope.launch {
            val response = repo.fetchCharacters(page)
            isAbleToLoadMore = response.info.next != null
            page++
            _characters.postValue(response.results)
        }
    }

}




