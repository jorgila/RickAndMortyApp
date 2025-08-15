package com.jorgila.rickandmortyapp.data.database

import android.content.Context
import androidx.room.ConstructedBy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.jorgila.rickandmortyapp.RickAndMortyApp
import kotlinx.coroutines.Dispatchers

fun getDatabaseBuilder(): RoomDatabase.Builder<RickAndMortyDatabase> {
    val context : Context = RickAndMortyApp.applicationContextInstance
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<RickAndMortyDatabase>(context, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .addMigrations()
}
