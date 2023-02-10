package com.example.dogify.db

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class RealmDb {
    private val configuration =
        RealmConfiguration.Builder(schema = setOf(BreedRealm::class)).apply {
            deleteRealmIfMigrationNeeded()
        }.build()

    val realm = Realm.open(configuration)
}