package com.shakenbeer.notes.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.UUID

@Entity(tableName = "notes")
class Note {

    @PrimaryKey
    var id: UUID = UUID.randomUUID()
    var createdAt: Instant = Instant.now()

    @Embedded
    var content: NodeContent = NodeContent.EMPTY

    @Embedded(prefix = "reminder_")
    var reminder: Reminder? = null

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
