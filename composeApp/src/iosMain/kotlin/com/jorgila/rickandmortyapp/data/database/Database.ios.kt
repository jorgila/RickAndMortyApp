package com.jorgila.rickandmortyapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSUserDomainMask
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
@OptIn(ExperimentalForeignApi::class)
fun fileDirectory() : String {
    val documentDirectory : NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    return requireNotNull(documentDirectory).path!!
}

fun getDatabaseBuilder(): RoomDatabase.Builder<RickAndMortyDatabase> {
    val dbFile = "${fileDirectory()}/$DATABASE_NAME"

    return Room.databaseBuilder<RickAndMortyDatabase>(name = dbFile)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}