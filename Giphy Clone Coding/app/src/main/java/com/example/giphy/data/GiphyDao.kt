package com.example.giphy.data

import androidx.room.*
import com.example.giphy.model.room.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface GiphyDao {
    @Query("SELECT * FROM favorite_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}