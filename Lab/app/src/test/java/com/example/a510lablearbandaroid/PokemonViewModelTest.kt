package com.example.a510lablearbandaroid

import com.example.a510lablearbandaroid.Utils.PokedexResponse
import com.example.a510lablearbandaroid.Utils.PokemonApiService
import com.example.a510lablearbandaroid.Utils.PokemonEntry
import com.example.a510lablearbandaroid.Utils.PokemonSpecies
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var apiService: PokemonApiService
    private lateinit var viewModel: PokemonViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        apiService = mockk<PokemonApiService>()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchPokemon succeeds and updates pokemonList`() = runTest {
        // 1. Prepare
        val mockPokemonList = listOf(
            PokemonEntry(1, PokemonSpecies("bulbasaur", "url1")),
            PokemonEntry(2, PokemonSpecies("ivysaur", "url2"))
        )
        val mockResponse = PokedexResponse(mockPokemonList)

        coEvery { apiService.getKantoPokedex() } returns mockResponse

        // 2. Action
        viewModel = PokemonViewModel(apiService)
        advanceUntilIdle()

        // 3. Assert
        val actualList = viewModel.pokemonList.value
        assertEquals(2, actualList.size)
        assertEquals("bulbasaur", actualList[0].pokemon_species.name)
        assertEquals("ivysaur", actualList[1].pokemon_species.name)
    }

    @Test
    fun `fetchPokemon fails and list remains empty`() = runTest {
        // 1. Prepare
        coEvery { apiService.getKantoPokedex() } throws Exception("Network Error")

        // 2. Action
        viewModel = PokemonViewModel(apiService)
        advanceUntilIdle()

        // 3. Assert
        assertEquals(0, viewModel.pokemonList.value.size)
    }
}
