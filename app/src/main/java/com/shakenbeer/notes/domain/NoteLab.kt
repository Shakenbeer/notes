package com.shakenbeer.notes.domain

class NoteLab(
    val noteRepository: NoteRepository,
    val notificationService: NotificationService
) {
    suspend fun notes() = noteRepository.getNotes()
    suspend fun save(note: Note) {
        noteRepository.saveNote(note)
        if (note.isScheduled)
            notificationService.scheduleNotification(note)
        else
            notificationService.clearNotification(note)
    }
    suspend fun delete(note: Note) = noteRepository.deleteNote(note)
}