package com.example.dogify.useCases

import com.example.dogify.model.Breed
import com.example.dogify.repo.BreedRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetBreedsUseCase:KoinComponent {

    //We are not using Koin Constructor injection, this is KoinComponent to prevent inject through View layer , we will see
    private val breedsRepository:BreedRepository by inject()

    suspend fun invoke():List<Breed> = breedsRepository.get()
}