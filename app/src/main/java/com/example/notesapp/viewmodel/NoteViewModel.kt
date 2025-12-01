package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesapp.model.Note
import com.example.notesapp.repositories.NoteRepository
import org.mongodb.kbson.ObjectId

class NoteViewModel: ViewModel() {
    private val repository = NoteRepository()

    fun addNote(title: String, content: String) {
        repository.addNote(title, content,)
    }

    fun deleteNoteById(noteId: ObjectId) {
        repository.deleteNoteById(noteId)
    }

    fun editNote(note: Note, newTitle: String, newContent: String) {
        repository.editNote(note, newTitle, newContent)
    }
}