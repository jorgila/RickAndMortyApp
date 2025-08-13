package com.jorgila.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import com.jorgila.rickandmortyapp.data.database.DATABASE_NAME
import com.jorgila.rickandmortyapp.data.database.RickAndMortyDatabase
import com.jorgila.rickandmortyapp.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single<RickAndMortyDatabase> { getDatabase(get())}
    }
}