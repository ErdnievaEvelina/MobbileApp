package com.example.mobileappeducation.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mobileappeducation.data.BookEntity

data class BookState(
    val title: MutableState<String> = mutableStateOf(""),
    val author: MutableState<String> = mutableStateOf(""),
    val image:MutableState<String> = mutableStateOf(""),
    val subtitle:MutableState<String> = mutableStateOf(""),
    val url:MutableState<String> = mutableStateOf(""),
    val books:List<BookEntity> = emptyList()
)
