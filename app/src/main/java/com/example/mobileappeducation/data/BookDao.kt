package com.example.mobileappeducation.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Upsert
    suspend fun upsertBook(book:BookEntity)
    @Delete
    suspend fun deleteBook(book:BookEntity)
    @Query("select * from bookentity order by title")
    fun getNotesOrderedByTitle(): Flow<List<BookEntity>>
    @Query("select * from bookentity order by authors asc")
    fun getNotesOrderedByAuthors(): Flow<List<BookEntity>>
}