package com.shakenbeer.notes.domain

import java.util.UUID

class NoteLab(
    val noteRepository: NoteRepository,
    val notificationService: NotificationService
) {
    suspend fun notes() = noteRepository.getNotes()
    suspend fun getNote(id: UUID) = noteRepository.getNote(id)
    suspend fun save(note: Note) {
        noteRepository.saveNote(note)
        if (note.reminder != null)
            notificationService.scheduleNotification(note)
        else
            notificationService.clearNotification(note)
    }
    suspend fun delete(note: Note) = noteRepository.deleteNote(note)
}