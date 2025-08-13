package com.jorgila.rickandmortyapp.di

import com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.characters.CharactersViewModel
import com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.episodes.EpisodesViewModel
import com.jorgila.rickandmortyapp.ui.detail.CharacterDetailScreen
import com.jorgila.rickandmortyapp.ui.detail.CharacterDetailViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val UIModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterDetailViewModel)
}