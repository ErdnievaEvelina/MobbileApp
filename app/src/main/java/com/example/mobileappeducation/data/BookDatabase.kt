package com.example.mobileappeducation.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [BookEntity::class],
    version = 1
)
abstract class BookDatabase:RoomDatabase() {
    abstract val dao:BookDao

}