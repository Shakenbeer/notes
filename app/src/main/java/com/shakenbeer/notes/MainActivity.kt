package com.shakenbeer.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.shakenbeer.notes.ui.list.NoteListDestination
import com.shakenbeer.notes.ui.note.NoteDestination
import com.shakenbeer.notes.ui.note.NoteViewModel
import com.shakenbeer.notes.ui.theme.NotesTheme
import java.util.UUID

data object NoteListRoute
data class NoteRoute(val id: UUID)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesTheme {
                val backStack = remember { mutableStateListOf<Any>(NoteListRoute) }

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = entryProvider {
                        entry<NoteListRoute> {
                            NoteListDestination(
                                onNoteClick = { uuid -> backStack.add(NoteRoute(uuid)) },
                                onNewNoteClick = { backStack.add(NoteRoute(UUID.randomUUID())) }
                            )
                        }
                        entry<NoteRoute> { key ->
                            NoteDestination(
                                noteId = key.id,
                                viewModel = viewModel(factory = NoteViewModel.Factory(key.id))
                            )
                        }
                    }
                )
            }
        }
    }
}
