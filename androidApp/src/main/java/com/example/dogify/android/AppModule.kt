package com.example.dogify.android

import com.example.dogify.useCases.GetBreedsUseCase
import com.example.dogify.useCases.ToggleFavouriteStateUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModules = module {
    viewModelOf(::MainViewModel)
}