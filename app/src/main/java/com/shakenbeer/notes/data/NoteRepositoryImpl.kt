package com.shakenbeer.notes.data

import com.shakenbeer.notes.data.db.NoteDao
import com.shakenbeer.notes.domain.Note
import com.shakenbeer.notes.domain.NoteRepository
import java.util.UUID

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun getNotes(): List<Note> {
        return noteDao.getNotes()
    }

    override suspend fun getNote(id: UUID): Note? {
        return noteDao.getNote(id)
    }

    override suspend fun saveNote(note: Note) {
        noteDao.saveNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}
