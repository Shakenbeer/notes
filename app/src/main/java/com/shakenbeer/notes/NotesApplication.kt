package com.shakenbeer.notes

import android.app.Application

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}
