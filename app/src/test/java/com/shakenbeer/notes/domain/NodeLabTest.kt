package com.shakenbeer.notes.domain

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import java.time.Instant

class NodeLabTest {
    private val noteRepository = mock<NoteRepository>()
    private val notificationService = mock<NotificationService>()

    private val noteLab = NoteLab(noteRepository, notificationService)

    @Test
    fun `if reminder is not set when note is saved then notification is cleared`() = runBlocking {
        val note = Note(reminder = null)
        noteLab.save(note)
        verify(notificationService).clearNotification(note)
    }

    @Test
    fun `if reminder is set when note is saved then notification is scheduled`() = runBlocking {
        val note = Note(reminder = Reminder(Instant.now()))
        noteLab.save(note)
        verify(notificationService).scheduleNotification(note)
    }
}