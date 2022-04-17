package com.example.giphy.repository.databaseRepository

import com.example.giphy.data.GiphyDao
import com.example.giphy.model.room.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GiphyDatabaseRepository @Inject constructor(private val giphyDao: GiphyDao) {
    fun getFavorites(): Flow<List<Favorite>> = giphyDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = giphyDao.insertFavorite(favorite = favorite)
    suspend fun deleteFavorite(favorite: Favorite) = giphyDao.deleteFavorite(favorite = favorite)
}