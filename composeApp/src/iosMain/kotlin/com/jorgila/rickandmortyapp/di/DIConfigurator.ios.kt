package com.jorgila.rickandmortyapp.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.jorgila.rickandmortyapp.data.database.DATABASE_NAME
import com.jorgila.rickandmortyapp.data.database.RickAndMortyDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun iosFileDirectory(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    return requireNotNull(documentDirectory).path!!
}

actual fun platformModule(): Module {
    return module {
        single<RickAndMortyDatabase> {
            val dbFile = "${iosFileDirectory()}/$DATABASE_NAME"
            Room.databaseBuilder<RickAndMortyDatabase>(name = dbFile)
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
        single { get<RickAndMortyDatabase>().getPreferencesDao() }
    }
}