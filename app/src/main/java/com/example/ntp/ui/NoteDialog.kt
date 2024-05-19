package com.example.ntp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewModelScope
import com.example.ntp.R
import com.example.ntp.data.Note
import com.example.ntp.data.NoteViewModel
import kotlinx.coroutines.launch

@Composable
fun NoteDialog(
    viewModel: NoteViewModel, openDialog: MutableState<Boolean>,
    title: String = "", content: String = "", id: Int? = null
) {
    var newTitle by remember { mutableStateOf(title) }
    var newContent by remember { mutableStateOf(content) }
    Dialog(onDismissRequest = { openDialog.value = false }) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                if (id != null) {
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            viewModel.viewModelScope.launch {
                                viewModel.deleteNote(
                                    note = Note(
                                        id = id,
                                        title = title,
                                        content = content
                                    )
                                )
                            }
                            openDialog.value = false
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = stringResource(R.string.delete),
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
                TextField(
                    value = newTitle,
                    onValueChange = { newTitle = it },
                    placeholder = { Text(text = stringResource(R.string.title)) },
                    textStyle = MaterialTheme.typography.titleLarge
                )
                TextField(
                    value = newContent,
                    onValueChange = { newContent = it },
                    placeholder = { Text(text = stringResource(R.string.note)) },
                    textStyle = MaterialTheme.typography.bodyMedium
                )
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    TextButton(onClick = { openDialog.value = false }) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = {
                        if (newContent.isBlank()) return@TextButton
                        if (id == null)
                            viewModel.viewModelScope.launch {
                                viewModel.insertNote(Note(title = newTitle, content = newContent))
                            }
                        else {
                            val note = Note(id = id, title = newTitle, content = newContent)
                            viewModel.viewModelScope.launch {
                                viewModel.updateNote(note)
                            }
                        }
                        openDialog.value = false
                    }) {
                        Text(
                            text = stringResource(R.string.save),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}