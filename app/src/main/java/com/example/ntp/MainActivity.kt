package com.example.ntp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.ntp.data.AppDatabase
import com.example.ntp.data.NoteRepository
import com.example.ntp.data.NoteViewModel
import com.example.ntp.screens.JokesScreen
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
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Route.main_screen) {
                    composable(route = Route.main_screen) {
                        MainScreen(navigateToNotes = {
                            navController.navigate(Route.notes_screen)
                        },
                            navigateToJokes = {
                                navController.navigate(Route.jokes_screen)
                            })
                    }
                    composable(route = Route.notes_screen) {
                        NotesScreen(viewModel = noteViewModel,
                            navigateBack = { navController.popBackStack() }
                        )
                    }
                    composable(route = Route.jokes_screen) {
                        JokesScreen(navigateBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

object Route {
    const val main_screen = "main_screen"
    const val notes_screen = "notes_screen"
    const val jokes_screen = "jokes_screen"
}
