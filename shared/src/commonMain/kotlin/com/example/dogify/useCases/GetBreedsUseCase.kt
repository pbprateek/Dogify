package com.example.dogify.useCases

import com.example.dogify.model.Breed

class GetBreedsUseCase {

    suspend fun invoke():List<Breed> = listOf(Breed("Test get","",false))
}