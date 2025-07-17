package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jorgila.rickandmortyapp.ui.core.navigation.Routes
import com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.characters.CharactersScreen
import com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Routes.Episodes.route
    ){
        composable(route = Routes.Episodes.route) {
            EpisodesScreen()
        }

        composable(route = Routes.Characters.route) {
            CharactersScreen()
        }
    }
}