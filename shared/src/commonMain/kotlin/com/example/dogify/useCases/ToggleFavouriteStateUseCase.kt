package com.example.dogify.useCases

import com.example.dogify.model.Breed
import com.example.dogify.repo.BreedRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ToggleFavouriteStateUseCase : KoinComponent {

    private val breedRepository: BreedRepository by inject()

    suspend operator fun invoke(breed: Breed) {
        breedRepository.update(breed.copy(isFavourite = !breed.isFavourite))
    }
}