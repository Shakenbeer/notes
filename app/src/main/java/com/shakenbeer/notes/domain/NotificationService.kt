package com.shakenbeer.notes.domain

interface NotificationService {
    fun scheduleNotification(note: Note)
    fun clearNotification(note: Note)
}
