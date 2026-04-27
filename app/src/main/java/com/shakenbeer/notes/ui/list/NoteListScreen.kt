package com.shakenbeer.notes.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.notes.R
import com.shakenbeer.notes.domain.NodeContent
import com.shakenbeer.notes.domain.Note
import com.shakenbeer.notes.domain.Reminder
import com.shakenbeer.notes.ui.theme.NotesTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID

@Composable
fun NoteListDestination(
    onNoteClick: (UUID) -> Unit,
    onNewNoteClick: () -> Unit,
    viewModel: NoteListViewModel = viewModel(factory = NoteListViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    NoteListScreen(
        uiState = uiState,
        onNoteClick = { note -> onNoteClick(note.id) },
        onNewNoteClick = onNewNoteClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteListScreen(
    modifier: Modifier = Modifier,
    uiState: NoteListUiState,
    onNoteClick: (Note) -> Unit,
    onNewNoteClick: () -> Unit,
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Notes") }) }) {
        Button(onClick = onNewNoteClick) { }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = modifier.padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.notes) { note ->
                NoteItem(note = note, onNoteClick = onNoteClick)
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    onNoteClick: (Note) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onNoteClick(note) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.content.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
                fontSize = 18.sp,
                color = Color(0xFF0F172A)
            )

            if (note.content.body.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note.content.body,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    fontSize = 14.sp,
                    color = Color(0xFF1E293B)
                )
            }

            note.reminder?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.clickable { /* TODO: Handle reminder click */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_clock_12px),
                        contentDescription = "Reminder",
                        modifier = Modifier.size(12.dp),
                        tint = Color(0xFF1E40AF)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    val formatter = DateTimeFormatter.ofPattern("dd MMM, HH:mm")
                        .withZone(ZoneId.systemDefault())
                    Text(
                        text = formatter.format(it.timestamp),
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 15.sp,
                        fontSize = 10.sp,
                        color = Color(0xFF1E40AF)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteListScreenPreview() {
    NotesTheme {
        NoteListScreen(
            uiState = NoteListUiState(
                notes = listOf(
                    Note(
                        content = NodeContent(
                            "Spaghetti Carbonara",
                            "A classic Roman pasta dish made with eggs, cheese, pancetta, and black pepper. The heat of the pasta cooks the eggs, creating a creamy sauce."
                        ),
                        reminder = Reminder(Instant.now().plusSeconds(3600))
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
                            ),
                            reminder = Reminder(Instant.now().plusSeconds(604800))
                        )
                )
            ),
            onNoteClick = {},
            onNewNoteClick = {}
        )
    }
}