package com.example.ntp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ntp.data.Note

@Composable
fun NoteCard(note: Note) {
    ElevatedCard(onClick = { /*TODO*/ },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Text(modifier = Modifier.padding(8.dp), text = note.content)
    }
}

