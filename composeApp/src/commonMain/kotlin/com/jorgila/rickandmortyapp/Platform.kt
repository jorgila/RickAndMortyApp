package com.jorgila.rickandmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform