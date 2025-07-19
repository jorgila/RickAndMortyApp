package com.jorgila.rickandmortyapp.di

import com.jorgila.rickandmortyapp.domain.useCase.GetRandomCharacterUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacterUseCase)
}