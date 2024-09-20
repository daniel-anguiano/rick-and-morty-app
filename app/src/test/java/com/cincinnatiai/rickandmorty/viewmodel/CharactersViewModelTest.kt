package com.cincinnatiai.rickandmorty.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cincinnatiai.rickandmorty.data.Character
import com.cincinnatiai.rickandmorty.data.CharacterResponse
import com.cincinnatiai.rickandmorty.data.Info
import com.cincinnatiai.rickandmorty.repository.CharactersRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharactersViewModelTest {

    private lateinit var viewModel: CharactersViewModel
    private val repo: CharactersRepository by lazy {
        MockCharactersRepository()
    }
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = CharactersViewModel(repo)
    }

    @Test
    fun `test that my viewmodel fetches data`() = runTest {
        // Given

        // When
        viewModel.fetchCharacters()

        // Then
        assertNotNull(viewModel.repo)
        assertFalse(viewModel.characters.value.isNullOrEmpty())
        assertEquals(2, viewModel.page)
        assertFalse(viewModel.isAbleToLoadMore)
    }

    @Test
    fun `test that my viewmodel does not fetch data if unable`() = runTest {
        // Given
        viewModel._characters.value = emptyList()
        viewModel.isAbleToLoadMore = false

        // When
        viewModel.fetchCharacters()

        // Then
        assertNotNull(viewModel.repo)
        assertTrue(viewModel.characters.value.isNullOrEmpty())
    }
}

class MockCharactersRepository : CharactersRepository {
    override suspend fun fetchCharacters(page: Int): CharacterResponse = CharacterResponse(Info(10, 1, null, null), mutableListOf(
        Character()
    ))
}