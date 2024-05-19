package com.example.ntp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
)