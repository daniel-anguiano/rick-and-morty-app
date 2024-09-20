package com.cincinnatiai.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cincinnatiai.rickandmorty.model.ApiInterface
import com.cincinnatiai.rickandmorty.data.Character
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyViewModel : ViewModel() {
    private val api: ApiInterface
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiInterface::class.java)
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            val allCharacters = mutableListOf<Character>()
            var page = 1
            var hasMorePages = true

            while (hasMorePages) {
                val response = api.getCharacters(page)
                allCharacters.addAll(response.results)
                hasMorePages = response.info.next != null
                page++
            }
            _characters.postValue(allCharacters)
        }
    }
}




