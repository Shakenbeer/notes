package com.shakenbeer.notes.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.shakenbeer.notes.domain.Note
import com.shakenbeer.notes.domain.NotificationService

class AndroidNotificationService(private val context: Context) : NotificationService {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun scheduleNotification(note: Note) {
        val reminder = note.reminder ?: return

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(NotificationReceiver.EXTRA_NOTE_ID, note.id.toString())
            putExtra(NotificationReceiver.EXTRA_NOTE_TITLE, note.content.title)
            putExtra(NotificationReceiver.EXTRA_NOTE_BODY, note.content.body)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            note.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    reminder.timestamp.toEpochMilli(),
                    pendingIntent
                )
            }
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                reminder.timestamp.toEpochMilli(),
                pendingIntent
            )
        }
    }

    override fun clearNotification(note: Note) {
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            note.id.hashCode(),
            intent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent)
        }
    }
}
