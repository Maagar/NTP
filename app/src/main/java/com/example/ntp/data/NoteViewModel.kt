package com.example.ntp.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    val allNotes: Flow<List<Note>> = noteRepository.allNotes

    suspend fun insertNote(note: Note) {
        noteRepository.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteRepository.insertNote(note)
    }

}