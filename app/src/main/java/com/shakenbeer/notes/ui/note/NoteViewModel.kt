package com.shakenbeer.notes.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shakenbeer.notes.Injector
import com.shakenbeer.notes.domain.Note
import com.shakenbeer.notes.domain.NoteLab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class NoteViewModel(
    private val noteId: UUID,
    private val noteLab: NoteLab
) : ViewModel() {

    private val _uiState = MutableStateFlow(Note(noteId))
    val uiState: StateFlow<Note> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            noteLab.getNote(noteId)?.let { note ->
                _uiState.value = note
            }
        }
    }

    companion object {
        fun Factory(noteId: UUID): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NoteViewModel(noteId, Injector.noteLab) as T
            }
        }
    }
}
