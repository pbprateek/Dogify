package com.example.dogify.useCases

import com.example.dogify.model.Breed
import com.example.dogify.repo.BreedRepository
import kotlinx.coroutines.flow.Flow

internal class GetBreedsUseCase(private val breedsRepository:BreedRepository) {
    fun invoke(): Flow<List<Breed>> = breedsRepository.get()
}