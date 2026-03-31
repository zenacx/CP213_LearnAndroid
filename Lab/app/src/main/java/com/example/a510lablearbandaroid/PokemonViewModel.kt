package com.example.a510lablearbandaroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a510lablearbandaroid.Utils.PokemonApiService
import com.example.a510lablearbandaroid.Utils.PokemonEntry
import com.example.a510lablearbandaroid.Utils.PokemonNetwork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val apiService: PokemonApiService = PokemonNetwork.api
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonEntry>>(emptyList())
    val pokemonList = _pokemonList.asStateFlow()

    init {
        fetchPokemon()
    }

    fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val response = apiService.getKantoPokedex()
                _pokemonList.value = response.pokemon_entries
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}