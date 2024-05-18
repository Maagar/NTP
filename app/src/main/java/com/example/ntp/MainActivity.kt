package com.example.ntp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.ntp.data.AppDatabase
import com.example.ntp.data.NoteRepository
import com.example.ntp.data.NoteViewModel
import com.example.ntp.screens.MainScreen
import com.example.ntp.screens.NotesScreen
import com.example.ntp.ui.theme.NTPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "notes-database"
        ).build()
        val noteRepository = NoteRepository(db.noteDao())
        val noteViewModel = NoteViewModel(noteRepository)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NTPTheme {
                NotesScreen(viewModel = noteViewModel)
            }
        }
    }
}