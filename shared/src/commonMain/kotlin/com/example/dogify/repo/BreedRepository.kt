package com.example.dogify.repo

import com.example.dogify.model.Breed
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

internal class BreedRepository(
    private val localSource: BreedLocalSource,
    private val remoteSource: BreedRemoteSource
) {

    fun get(): Flow<List<Breed>> {
        return localSource.selectAll()
    }

    //Supervisor Scope to prevent cancellation of parent bcz of any child job failure
    suspend fun fetch() {
        coroutineScope {
            //Basically it will give all breeds and then we map it to a deferred and call await all on Collection<Deferred>
            remoteSource.getBreed().map {
                async {
                    val imageUrl = remoteSource.getBreedImage(it)
                    Breed(name = it, imageUrl = imageUrl, isFavourite = false)
                }
            }.awaitAll().also {
                localSource.insertWithUpdate(it)
            }
        }
    }

    suspend fun update(name: String, isFavourite: Boolean) {
        localSource.update(name, isFavourite)
    }

}