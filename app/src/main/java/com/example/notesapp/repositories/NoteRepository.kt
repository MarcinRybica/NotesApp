package com.example.notesapp.repositories

import com.example.notesapp.model.Note
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class NoteRepository {
    private val realm: Realm by lazy{
        val config = RealmConfiguration.Builder(schema = setOf(Note::class))
            .name("notes.realm")
            .build()
        Realm.open(config)
    }

    fun addNote(title: String, content: String, tags: List<String> = emptyList()){
        realm.writeBlocking{
            copyToRealm(Note().apply{
                this.title = title
                this.content = content
                this.tags.addAll(tags)
            })
        }
    }

    fun deleteNote(note: Note) {
        realm.writeBlocking {
            findLatest(note)?.let {
                delete(it)
            }
        }
    }

    fun editNote(note: Note, title: String, content: String) {
        realm.writeBlocking {
            findLatest(note)?.apply {
                this.title = title
                this.content = content
                this.updatedAt = System.currentTimeMillis()
            }
        }
    }

}