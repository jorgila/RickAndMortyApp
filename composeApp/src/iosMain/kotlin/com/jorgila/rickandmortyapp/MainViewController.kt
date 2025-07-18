package com.jorgila.rickandmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.jorgila.rickandmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() } ) { App() }