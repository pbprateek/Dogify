package com.example.dogify.repo

import com.example.dogify.api.BreedsApi
import com.example.dogify.util.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class BreedRemoteSource(
    private val api: BreedsApi,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun getBreed() = withContext(dispatcherProvider.io) {
        api.getBreeds().breeds
    }

    suspend fun getBreedImage(breed: String) = withContext(dispatcherProvider.io) {
        api.getRandomBreedImageFor(breed).breedImageUrl
    }


}