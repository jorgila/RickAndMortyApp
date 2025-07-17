package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.jorgila.rickandmortyapp.ui.core.navigation.Routes
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.ic_characters
import rickandmortyapp.composeapp.generated.resources.ic_player

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon:  @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_player),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
    ) : BottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_characters),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
    ) : BottomBarItem()
}