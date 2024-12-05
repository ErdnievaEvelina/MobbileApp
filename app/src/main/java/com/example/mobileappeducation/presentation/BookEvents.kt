package com.example.mobileappeducation.presentation

import com.example.mobileappeducation.data.BookEntity

sealed interface  BookEvents {
    object SortBooks:BookEvents
    data class DeleteBook(val book: BookEntity):BookEvents
    data class SaveBook(
        val title:String,
        val author:String,
        val image:String
    ):BookEvents
}