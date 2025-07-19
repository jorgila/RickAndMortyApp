package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.domain.useCase.GetRandomCharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(private val getRandomCharacterUseCase: GetRandomCharacterUseCase) : ViewModel() {
    private val _state = MutableStateFlow(CharactersState())
    val state : StateFlow<CharactersState> get() = _state

    init {
        viewModelScope.launch {
            val result: CharacterModel = withContext(Dispatchers.IO){
                getRandomCharacterUseCase()
            }
            _state.update { state ->
                state.copy(
                    characterOfTheDay = result
                )
            }
        }
    }
}