package com.example.giphy.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.giphy.model.room.Favorite

@Database(entities = [Favorite::class], version = 2, exportSchema = false)
abstract class GiphyDatabase : RoomDatabase() {
    abstract fun giphyDao(): GiphyDao
}