package com.example.mobileappeducation.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileappeducation.data.BookDao
import com.example.mobileappeducation.data.BookEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookViewModel(
    private val dao:BookDao
):ViewModel() {
    private val isSortedByTitle= MutableStateFlow(true)
    private var books=isSortedByTitle.flatMapLatest { sort->
        if (sort){
            dao.getNotesOrderedByTitle()
        }else{
            dao.getNotesOrderedByAuthors()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state= MutableStateFlow(BookState())
    val state= combine(_state,isSortedByTitle,books){ state, isSortedByTitle, books->
        state.copy(books=books)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),BookState())

    fun onEvent(event:BookEvents){
        when(event){
            is BookEvents.DeleteBook -> {
                viewModelScope.launch {
                    dao.deleteBook(event.book)
                }
            }
            is BookEvents.SaveBook -> {
                val book=BookEntity(
                    title = state.value.title.value,
                    authors = state.value.author.value,
                    image = state.value.image.value,
                    subtitle = state.value.subtitle.value,
                    url = state.value.url.value
                )
                viewModelScope.launch {
                    dao.upsertBook(book)
                }
                _state.update {
                    it.copy(
                        title= mutableStateOf(""),
                        author = mutableStateOf(""),
                        image = mutableStateOf(""),
                        subtitle = mutableStateOf(""),
                        url= mutableStateOf("")
                    )
                }

            }
            BookEvents.SortBooks -> {
                isSortedByTitle.value=!isSortedByTitle.value
            }
        }
    }
}