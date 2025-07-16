package com.jorgila.rickandmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jorgila.rickandmortyapp.ui.home.HomeScreen

@Composable
fun NavigationWrapper(){
    val mainNavController = rememberNavController()

    androidx.navigation.compose.NavHost(navController = mainNavController, startDestination = Routes.Home.route){
        composable(route = Routes.Home.route) {
            HomeScreen()
        }
    }
}