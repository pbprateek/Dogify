package com.example.dogify.useCases

import com.example.dogify.repo.BreedRepository

internal class ToggleFavouriteStateUseCase(private val breedRepository: BreedRepository) {

    suspend operator fun invoke(name: String, isFavourite: Boolean) {
        breedRepository.update(name, isFavourite)
    }
}