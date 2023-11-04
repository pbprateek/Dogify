package com.example.dogify.di

import com.example.dogify.api.BreedsApi
import com.example.dogify.db.RealmDb
import com.example.dogify.repo.BreedLocalSource
import com.example.dogify.repo.BreedRemoteSource
import com.example.dogify.repo.BreedRepository
import com.example.dogify.useCases.FetchBreedsUseCase
import com.example.dogify.useCases.GetBreedsUseCase
import com.example.dogify.useCases.ToggleFavouriteStateUseCase
import com.example.dogify.util.DispatcherProvider
import com.example.dogify.util.DispatcherProviderImpl
import com.example.dogify.view.MainViewModel
import org.koin.dsl.module

private val useCaseModule = module {
    factory { GetBreedsUseCase(get()) }
    factory { FetchBreedsUseCase(get()) }
    factory { ToggleFavouriteStateUseCase(get()) }
}


private val apiModule = module {
    single { BreedsApi() }
}

private val realm = module {
    single { RealmDb() }
}

private val utilityModule = module {
    factory<DispatcherProvider> { DispatcherProviderImpl() }
}

private val repositoryModule = module {
    factory { BreedRepository(get(), get()) }
    factory { BreedRemoteSource(get(), get()) }
    factory { BreedLocalSource(get(), get()) }

}

private val viewModelModules = module {
    factory { MainViewModel(get(), get(), get()) }
}

val sharedModule = listOf(useCaseModule, apiModule, utilityModule, repositoryModule, realm, viewModelModules)