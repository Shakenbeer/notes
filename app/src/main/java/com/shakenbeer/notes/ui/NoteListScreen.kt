package com.shakenbeer.notes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.notes.domain.NodeContent
import com.shakenbeer.notes.domain.Note
import com.shakenbeer.notes.ui.theme.NotesTheme

@Composable
fun NoteListDestination(
    modifier: Modifier = Modifier,
    viewModel: NoteListViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsState()

    NoteListScreen(modifier, uiState)
}

@Composable
private fun NoteListScreen(
    modifier: Modifier = Modifier,
    uiState: NoteListUiState
) {
    Column(modifier = modifier) {
        LazyColumn {
            items(uiState.notes) { note ->
                Text(
                    text = note.content.title,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteListScreenPreview() {
    NotesTheme {
        Scaffold(topBar = { TopAppBar(title = { Text("Notes") }) }) {
            NoteListScreen(
                modifier = Modifier.padding(it),
                uiState = NoteListUiState(
                    notes = listOf(
                        Note(
                            content = NodeContent(
                                "Spaghetti Carbonara",
                                "A classic Roman pasta dish made with eggs, cheese, pancetta, and black pepper. The heat of the pasta cooks the eggs, creating a creamy sauce."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Chicken Tikka Masala",
                                "Chunks of roasted marinated chicken in a spiced curry sauce. The sauce is usually creamy and orange-colored."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Classic Beef Tacos",
                                "Seasoned ground beef, cheese, lettuce, and tomatoes in a crispy or soft taco shell. A favorite for a quick and satisfying meal."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Mushroom Risotto",
                                "A creamy Italian rice dish made with Arborio rice, mushrooms, and Parmesan cheese. It requires constant stirring to achieve its signature texture."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Caesar Salad",
                                "A green salad of romaine lettuce and croutons dressed with lemon juice, olive oil, egg, Worcestershire sauce, anchovies, and Parmesan cheese."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Beef Stroganoff",
                                "A Russian dish of sautéed pieces of beef served in a sauce with smetana (sour cream). Often served over noodles or rice."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Pad Thai",
                                "A stir-fried rice noodle dish commonly served as a street food in Thailand, made with shrimp or chicken, peanuts, a scrambled egg, and bean sprouts."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Shepherd's Pie",
                                "A savory pie with a filling of minced lamb and a topping of mashed potato. A hearty and comforting dish."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Goulash",
                                "A soup or stew of meat and vegetables seasoned with paprika and other spices, originating in Hungary. It's a warm and filling meal."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Ratatouille",
                                "A French Provençal stewed vegetable dish, originating in Nice, and sometimes referred to as ratatouille niçoise."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Jambalaya",
                                "A Louisiana Creole dish of Spanish and French influence, consisting mainly of meat and vegetables mixed with rice."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Fish and Chips",
                                "A hot dish consisting of fried fish in batter, served with chips. A popular takeaway food in the United Kingdom and numerous other countries."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Lasagna",
                                "A type of wide, flat pasta, layered with sauces and various ingredients such as meats, vegetables, and cheese."
                            )
                        ),
                        Note(
                            content = NodeContent(
                                "Borsch",
                                "A sour soup common in Eastern Europe and Northern Asia. In English, the word borsch is most often associated with the soup's variant of Ukrainian origin, made with beetroots as one of the main ingredients."
                            )
                        )
                    )
                )
            )
        }
    }
}