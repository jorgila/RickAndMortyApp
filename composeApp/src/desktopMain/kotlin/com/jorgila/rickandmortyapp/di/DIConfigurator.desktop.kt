package com.jorgila.rickandmortyapp.di

import com.jorgila.rickandmortyapp.data.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single { getDatabaseBuilder() }
    }
}