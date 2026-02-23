package com.shakenbeer.notes.ui.note

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.notes.domain.Note
import java.util.UUID

@Composable
fun NoteDestination(noteId: UUID) {
    val viewModel: NoteViewModel = viewModel(factory = NoteViewModel.Factory(noteId))
    val note by viewModel.uiState.collectAsState()
    NoteScreen(note = note)
}

@Composable
fun NoteScreen(note: Note) {
    OutlinedTextField(
        value = note.content.title,
        onValueChange = { /* TODO */ }
    )
}
