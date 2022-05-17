package com.example.dogify.di

import com.example.dogify.useCases.FetchBreedsUseCase
import com.example.dogify.useCases.GetBreedsUseCase
import com.example.dogify.useCases.ToggleFavouriteStateUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

class KoinModule {
}

private val useCaseModule = module {
    factory { GetBreedsUseCase() }
    factory { FetchBreedsUseCase() }
    factory { ToggleFavouriteStateUseCase() }
}

private val sharedModule = listOf(useCaseModule)

fun initKoin(appDeclaration: KoinAppDeclaration = {}){
    startKoin {
        appDeclaration()
        modules(sharedModule)
    }
}