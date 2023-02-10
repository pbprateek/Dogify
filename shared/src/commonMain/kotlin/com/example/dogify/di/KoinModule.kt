package com.example.dogify.di

import com.example.dogify.api.BreedsApi
import com.example.dogify.db.RealmDb
import com.example.dogify.repo.BreedLocalSource
import com.example.dogify.repo.BreedRemoteSource
import com.example.dogify.repo.BreedRepository
import com.example.dogify.useCases.FetchBreedsUseCase
import com.example.dogify.useCases.GetBreedsUseCase
import com.example.dogify.useCases.ToggleFavouriteStateUseCase
import com.example.dogify.util.getDispatcherProvider
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


private val apiModule = module {
    single { BreedsApi() }
}

private val realm = module {
    single { RealmDb() }
}

private val utilityModule = module {
    factory { getDispatcherProvider() }
}

private val repositoryModule = module {
    factory { BreedRepository(get(),get()) }
    factory { BreedRemoteSource(get(), get()) }
    factory { BreedLocalSource(get(),get()) }

}

private val sharedModule = listOf(useCaseModule, apiModule, utilityModule, repositoryModule, realm)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(sharedModule)
    }
}