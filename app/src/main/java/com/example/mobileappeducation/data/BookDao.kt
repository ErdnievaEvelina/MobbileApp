package com.example.mobileappeducation.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Upsert
    suspend fun upsertNote(book:BookEntity)
    @Delete
    suspend fun deleteNote(book:BookEntity)
    @Query("select * from bookentity order by title")
    fun getNotesOrderedByDateAdded(): Flow<List<BookEntity>>
    @Query("select * from bookentity order by authors asc")
    fun getNotesOrderedByTitle(): Flow<List<BookEntity>>
}