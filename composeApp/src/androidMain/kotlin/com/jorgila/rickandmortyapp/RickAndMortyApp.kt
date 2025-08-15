package com.jorgila.rickandmortyapp

import android.app.Application
import android.content.Context
import androidx.compose.runtime.tooling.CompositionInstance
import com.jorgila.rickandmortyapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickAndMortyApp : Application() {

    companion object {
        lateinit var applicationContextInstance: Context
            private set
    }


    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidLogger()
            androidContext(this@RickAndMortyApp)
        }
        applicationContextInstance = applicationContext
    }
}