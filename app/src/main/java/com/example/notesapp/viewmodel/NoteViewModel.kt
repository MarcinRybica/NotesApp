package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesapp.repositories.NoteRepository

class NoteViewModel: ViewModel() {
    private val repository = NoteRepository()

    fun addNote(title: String, content: String, tags: List<String> = emptyList()) {
        repository.addNote(title, content, tags)
    }
}