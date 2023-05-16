package com.example.dogify.useCases

import com.example.dogify.repo.BreedRepository

internal class FetchBreedsUseCase(private val breedsRepository: BreedRepository) {
    suspend fun invoke() = breedsRepository.fetch()
}