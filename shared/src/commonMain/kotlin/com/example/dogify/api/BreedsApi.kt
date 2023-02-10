package com.example.dogify.api

import com.example.dogify.model.BreedImageResponse
import com.example.dogify.model.BreedResponse
import io.ktor.client.call.*
import io.ktor.client.request.*

internal class BreedsApi : KtorApi() {

    suspend fun getBreeds(): BreedResponse = client.get {
        apiUrl("breeds/list")
    }.body()

    suspend fun getRandomBreedImageFor(breed: String): BreedImageResponse = client.get {
        apiUrl("breed/$breed/images/random")
    }.body()
}