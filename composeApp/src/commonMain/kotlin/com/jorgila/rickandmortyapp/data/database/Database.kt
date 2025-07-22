package com.jorgila.rickandmortyapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.jorgila.rickandmortyapp.data.database.dao.UserPreferencesDAO
import com.jorgila.rickandmortyapp.data.database.entity.CharacterOfTheDayEntity

const val DATABASE_NAME = "rm_app_database.db"

expect object RickAndMortyCTor: RoomDatabaseConstructor<RickAndMortyDatabase>

@Database( entities = [ CharacterOfTheDayEntity::class ], version = 1 )
@ConstructedBy(RickAndMortyCTor::class)
abstract class RickAndMortyDatabase:RoomDatabase(){
    // UserPreferencesDao
    abstract fun getPreferencesDao() : UserPreferencesDAO

}