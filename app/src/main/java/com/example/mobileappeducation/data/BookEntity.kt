package com.example.mobileappeducation.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id       : String,
    val title    : String,
    val subtitle : String,
    val authors  : String,
    val image    : String,
    val url      : String
)
