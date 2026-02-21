package com.shakenbeer.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shakenbeer.notes.Injector

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NoteListViewModel::class.java) -> {
                NoteListViewModel(Injector.noteLab) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
