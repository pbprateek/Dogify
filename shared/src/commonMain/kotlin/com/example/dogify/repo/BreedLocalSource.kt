package com.example.dogify.repo

import com.example.dogify.db.BreedRealm
import com.example.dogify.db.RealmDb
import com.example.dogify.model.Breed
import com.example.dogify.util.DispatcherProvider
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class BreedLocalSource(
    private val realmDb: RealmDb,
    private val dispatcherProvider: DispatcherProvider
) {

    fun selectAll(): Flow<List<Breed>> {
        val breedsFlow = realmDb.realm.query<BreedRealm>().find().asFlow().map {
            it.list.map { breed ->
                Breed(breed.name, breed.imageUrl, breed.isFavourite)
            }
            /*
            when (it) {
                is InitialResults -> {
                    it.list.map { breed ->
                        Breed(breed.name, breed.imageUrl, breed.isFavourite)
                    }
                }
                is UpdatedResults -> {
                    it.list.map { breed ->
                        Breed(breed.name, breed.imageUrl, breed.isFavourite)
                    }
                }
            }
             */
        }.flowOn(dispatcherProvider.io)

        return breedsFlow
    }

    //Error observed in realm is if u fire suppose 50-60 insert queries in parallel
    //realm update listener stops working or crashed inside, so try to do bulk insert
    suspend fun insertWithUpdate(breeds: List<Breed>) = withContext(dispatcherProvider.io) {
        realmDb.realm.write {
            breeds.forEach { breed ->
                val breedRealm: BreedRealm? =
                    this.query<BreedRealm>("name == $0", breed.name).first().find()
                if (breedRealm == null) {
                    copyToRealm(BreedRealm().apply {
                        name = breed.name
                        imageUrl = breed.imageUrl
                        isFavourite = breed.isFavourite
                    })
                } else {
                    breedRealm.imageUrl = breed.imageUrl
                }
            }
        }
    }

    suspend fun update(name: String, isFavourite: Boolean) = withContext(dispatcherProvider.io) {
        realmDb.realm.write {
            val breedRealm: BreedRealm? =
                this.query<BreedRealm>("name == $0", name).first().find()
            breedRealm?.isFavourite = isFavourite
        }
    }

    suspend fun clear() = withContext(dispatcherProvider.io) {
        realmDb.realm.write {
            val breeds: RealmResults<BreedRealm> = this.query<BreedRealm>().find()
            delete(breeds)
        }
    }


}