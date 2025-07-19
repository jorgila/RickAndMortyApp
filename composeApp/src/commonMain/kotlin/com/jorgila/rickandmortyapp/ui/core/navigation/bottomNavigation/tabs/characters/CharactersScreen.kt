package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharactersScreen(){
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
}
