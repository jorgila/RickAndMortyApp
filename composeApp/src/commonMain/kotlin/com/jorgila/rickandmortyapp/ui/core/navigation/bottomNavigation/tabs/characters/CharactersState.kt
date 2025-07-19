package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.characters

import com.jorgila.rickandmortyapp.domain.model.CharacterModel

data class CharactersState(
    val characterOfTheDay: CharacterModel? = null
)
