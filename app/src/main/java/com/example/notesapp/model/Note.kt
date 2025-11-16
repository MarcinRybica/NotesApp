package com.example.notesapp.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Note : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId() //automatycznie generowane id
    var title: String = ""
    var content: String = ""
    var tags: RealmList<String> = realmListOf()
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()
    var favourite: Boolean = false
    var attachmentPath: String? = null

}