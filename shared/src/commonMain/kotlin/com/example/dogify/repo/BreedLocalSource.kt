package com.example.dogify.repo

import com.example.dogify.db.BreedRealm
import com.example.dogify.db.RealmDb
import com.example.dogify.model.Breed
import com.example.dogify.util.DispatcherProvider
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.withContext

internal class BreedLocalSource(
    private val realmDb: RealmDb,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun selectAll(): List<Breed> = withContext(dispatcherProvider.io) {
        val breedsRealm: RealmResults<BreedRealm> = realmDb.realm.query<BreedRealm>().find()
        return@withContext breedsRealm.map {
            Breed(it.name, it.imageUrl, it.isFavourite)
        }
    }

    suspend fun insert(breed: Breed) = withContext(dispatcherProvider.io) {
        realmDb.realm.write {
            copyToRealm(BreedRealm().apply {
                name = breed.name
                imageUrl = breed.imageUrl
                isFavourite = breed.isFavourite
            })
        }
    }

    suspend fun update(breed: Breed) = withContext(dispatcherProvider.io) {
        realmDb.realm.write {
            val breedRealm: BreedRealm? =
                this.query<BreedRealm>("name == $0",breed.name).first().find()
            breedRealm?.name = breed.name
            breedRealm?.imageUrl = breed.imageUrl
            breedRealm?.isFavourite = breed.isFavourite
        }
    }

    suspend fun clear() = withContext(dispatcherProvider.io) {
        realmDb.realm.write {
            val breeds: RealmResults<BreedRealm> = this.query<BreedRealm>().find()
            delete(breeds)

        }
    }


}