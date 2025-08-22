package com.jorgila.rickandmortyapp

import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jorgila.rickandmortyapp.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rick and Morty App"
    ){
        initKoin()
        App()
    }
}