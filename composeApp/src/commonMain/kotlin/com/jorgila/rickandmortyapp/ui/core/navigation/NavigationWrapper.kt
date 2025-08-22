package com.jorgila.rickandmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.ui.detail.CharacterDetailScreen
import com.jorgila.rickandmortyapp.ui.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavigationWrapper(){
    val mainNavController = rememberNavController()

    androidx.navigation.compose.NavHost(navController = mainNavController, startDestination = Routes.Home.route){
        composable(route = Routes.Home.route) {
            HomeScreen(mainNavController)
        }

        composable<CharacterDetail> { navBackStackEntry ->
            val characterDetailEncoding = navBackStackEntry.toRoute<CharacterDetail>()
            val characterModel = Json.decodeFromString<CharacterModel>(characterDetailEncoding.characterModel)
            CharacterDetailScreen(
                characterModel = characterModel,
                onBackPressed = { mainNavController.popBackStack() }
            )
        }
    }
}