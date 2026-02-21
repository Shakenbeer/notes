package com.shakenbeer.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakenbeer.notes.domain.Note
import com.shakenbeer.notes.domain.NoteLab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class NoteListUiState(
    val notes: List<Note> = emptyList()
)

class NoteListViewModel(private val noteLab: NoteLab) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteListUiState())
    val uiState: StateFlow<NoteListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = NoteListUiState(notes = noteLab.notes())
        }
    }
}
