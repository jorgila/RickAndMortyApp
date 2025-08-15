package com.jorgila.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.jorgila.rickandmortyapp.data.database.DATABASE_NAME
import com.jorgila.rickandmortyapp.data.database.RickAndMortyDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single<RickAndMortyDatabase> {
            val context: Context = androidContext()
            val dbFile = context.getDatabasePath(DATABASE_NAME)
            Room.databaseBuilder<RickAndMortyDatabase>(
                context,
                dbFile.absolutePath
            ).setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
        single { get<RickAndMortyDatabase>().getPreferencesDao() }
    }
}