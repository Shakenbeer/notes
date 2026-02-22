package com.shakenbeer.notes.ui.list

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

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NoteListViewModel(Injector.noteLab) as T
            }
        }
    }
}
