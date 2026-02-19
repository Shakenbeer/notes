package com.shakenbeer.notes.domain

interface NoteRepository {
    suspend fun getNotes(): List<Note>
    suspend fun saveNote(note: Note)
    suspend fun deleteNote(note: Note)
}
