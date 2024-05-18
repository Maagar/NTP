package com.example.ntp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ntp.data.Note
import com.example.ntp.data.NoteViewModel
import com.example.ntp.ui.NoteCard

@Composable
fun NotesScreen(modifier: Modifier = Modifier, viewModel: NoteViewModel) {
    val notes = viewModel.allNotes.collectAsState(initial = emptyList())
    Scaffold { innerPadding ->
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),

            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()) {
            items(notes.value) {item: Note ->
                NoteCard(note = item)
            }
        }
    }
    FloatingActionButton(onClick = { /*TODO*/ }) {
        
    }
}