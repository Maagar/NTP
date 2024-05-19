package com.example.ntp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ntp.R
import com.example.ntp.data.Note
import com.example.ntp.data.NoteViewModel
import com.example.ntp.ui.NoteCard
import com.example.ntp.ui.NoteDialog

@Composable
fun NotesScreen(viewModel: NoteViewModel, navigateBack: () -> Unit) {
    val notes = viewModel.allNotes.collectAsState(initial = emptyList())
    val openDialog = rememberSaveable { mutableStateOf(false) }
    val editNote = remember { mutableStateOf<Note?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { openDialog.value = true }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_note)
                )
            }
        }
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),

            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(notes.value) { item: Note ->
                NoteCard(note = item, onClick = {
                    openDialog.value = true
                    editNote.value = item
                })
            }
        }
    }
    if (openDialog.value && editNote.value == null) {
        NoteDialog(viewModel = viewModel, openDialog = openDialog)
    } else if (openDialog.value && editNote.value != null) {
        NoteDialog(
            viewModel = viewModel, openDialog = openDialog,
            title = editNote.value!!.title, content = editNote.value!!.content,
            id = editNote.value!!.id
        )
    } else editNote.value = null
}
