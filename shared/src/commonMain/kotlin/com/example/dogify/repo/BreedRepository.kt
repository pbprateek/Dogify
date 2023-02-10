package com.example.dogify.repo

import com.example.dogify.model.Breed
import kotlinx.coroutines.*

internal class BreedRepository(
    private val localSource: BreedLocalSource,
    private val remoteSource: BreedRemoteSource
) {

    suspend fun get(): List<Breed> = with(localSource.selectAll()) {
        if (isEmpty())
            return@with fetch()
        else
            this
    }

    //Supervisor Scope to prevent cancellation of parent bcz of any child job failure
    suspend fun fetch() = supervisorScope {
        //Basically it will give all breeds and then we map it to a deferred and call await all on Collection<Deferred>
        remoteSource.getBreed().map {
            async {
                val imageUrl = remoteSource.getBreedImage(it)
                Breed(name = it, imageUrl = imageUrl, isFavourite = false)
            }
        }.awaitAll().also {
            localSource.clear()
            it.map {
                async { localSource.insert(it) }
            }.awaitAll()
        }

    }

    suspend fun update(breed: Breed) {
        localSource.update(breed)
    }

}