package com.shakenbeer.notes.domain

import java.time.Instant
import java.util.UUID

class Note {

    val id: UUID = UUID.randomUUID()
    val createdAt: Instant = Instant.now()

    private var content: NodeContent = NodeContent.EMPTY

    private var reminder: Reminder? = null

    val title: String
        get() = content.title

    val body: String
        get() = content.body

    val isScheduled: Boolean
        get() = reminder != null

    val nextReminder: Instant?
        get() = reminder?.timestamp

    fun writeTitle(value: String) {
        content = content.copy(title = value)
    }

    fun writeBody(value: String) {
        content = content.copy(body = value)
    }

    fun setReminder(timestamp: Instant) {
        reminder = Reminder(timestamp)
    }

    fun deleteReminder() {
        reminder = null
    }
}
