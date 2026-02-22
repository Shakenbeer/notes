package com.shakenbeer.notes.domain

import java.util.UUID

interface NoteRepository {
    suspend fun getNotes(): List<Note>
    suspend fun getNote(id: UUID): Note?
    suspend fun saveNote(note: Note)
    suspend fun deleteNote(note: Note)
}
