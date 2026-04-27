package com.shakenbeer.notes.ui.note

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.shakenbeer.notes.domain.Note
import java.util.UUID

@Composable
fun NoteDestination(noteId: UUID, viewModel: NoteViewModel) {
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
