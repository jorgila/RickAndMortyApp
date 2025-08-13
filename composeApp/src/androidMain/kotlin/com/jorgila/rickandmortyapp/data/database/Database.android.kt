package com.jorgila.rickandmortyapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.jorgila.rickandmortyapp.data.database.dao.UserPreferencesDAO
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getDatabase(context:Context):RickAndMortyDatabase{
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<RickAndMortyDatabase>(context, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .addMigrations()
        .build()
}