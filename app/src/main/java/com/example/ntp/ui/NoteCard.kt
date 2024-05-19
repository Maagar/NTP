package com.example.ntp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.ntp.data.Note

@Composable
fun NoteCard(
    note: Note,
    onClick: () -> Unit,
) {
    ElevatedCard(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (note.title.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                modifier = Modifier.padding(4.dp),
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 6
            )
        }
    }
}

