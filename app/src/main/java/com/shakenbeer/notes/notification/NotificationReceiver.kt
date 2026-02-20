package com.shakenbeer.notes.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.shakenbeer.notes.R

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Reminders",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val noteId = intent.getStringExtra(EXTRA_NOTE_ID)
        val noteTitle = intent.getStringExtra(EXTRA_NOTE_TITLE)
        val noteBody = intent.getStringExtra(EXTRA_NOTE_BODY)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(noteTitle)
            .setContentText(noteBody)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        notificationManager.notify(noteId.hashCode(), notification)
    }

    companion object {
        const val CHANNEL_ID = "reminders_channel"
        const val EXTRA_NOTE_ID = "extra_note_id"
        const val EXTRA_NOTE_TITLE = "extra_note_title"
        const val EXTRA_NOTE_BODY = "extra_note_body"
    }
}
