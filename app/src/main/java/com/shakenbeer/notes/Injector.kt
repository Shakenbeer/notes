package com.shakenbeer.notes

import android.content.Context
import androidx.room.Room
import com.shakenbeer.notes.data.NoteRepositoryImpl
import com.shakenbeer.notes.data.db.NoteDatabase
import com.shakenbeer.notes.domain.NoteLab
import com.shakenbeer.notes.domain.NoteRepository
import com.shakenbeer.notes.notification.AndroidNotificationService

object Injector {

    private lateinit var applicationContext: Context

    private val noteDatabase: NoteDatabase by lazy {
        Room.databaseBuilder(applicationContext, NoteDatabase::class.java, "notes.db").build()
    }

    fun init(context: Context) {
        applicationContext = context.applicationContext
    }

    private fun getNoteRepository(): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao())
    }

    fun getNoteLab(): NoteLab {
        return NoteLab(getNoteRepository(), AndroidNotificationService(applicationContext))
    }
}
