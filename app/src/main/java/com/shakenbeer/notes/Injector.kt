package com.shakenbeer.notes

import android.content.Context
import androidx.room.Room
import com.shakenbeer.notes.data.NoteRepositoryImpl
import com.shakenbeer.notes.data.db.NoteDatabase
import com.shakenbeer.notes.domain.NoteRepository

object Injector {

    private lateinit var applicationContext: Context

    private val noteDatabase: NoteDatabase by lazy {
        Room.databaseBuilder(applicationContext, NoteDatabase::class.java, "notes.db").build()
    }

    fun init(context: Context) {
        applicationContext = context.applicationContext
    }

    fun getNoteRepository(): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao())
    }
}
