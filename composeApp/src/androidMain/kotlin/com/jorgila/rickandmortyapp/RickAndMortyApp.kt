package com.jorgila.rickandmortyapp

import android.app.Application
import com.jorgila.rickandmortyapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidLogger()
            androidContext(this@RickAndMortyApp)
        }
    }
}