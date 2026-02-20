package com.shakenbeer.notes.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.UUID

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val createdAt: Instant = Instant.now(),
    @Embedded
    val content: NodeContent = NodeContent.EMPTY,
    @Embedded(prefix = "reminder_")
    val reminder: Reminder? = null
)
