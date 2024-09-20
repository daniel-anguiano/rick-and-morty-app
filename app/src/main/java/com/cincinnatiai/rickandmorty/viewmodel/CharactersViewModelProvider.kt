package com.cincinnatiai.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cincinnatiai.rickandmorty.di.DIModule

class CharactersViewModelProvider : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            return CharactersViewModel(DIModule.charactersRepository) as T
        }
        throw IllegalArgumentException("You need the CharactersViewModel")
    }
}