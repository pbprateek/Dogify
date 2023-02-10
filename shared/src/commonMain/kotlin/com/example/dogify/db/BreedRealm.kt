package com.example.dogify.db

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class BreedRealm : RealmObject {
    @PrimaryKey
    var name: String = ""
    var imageUrl: String = ""
    var isFavourite: Boolean = false
}