package com.example.giphy.repository.homeRepository

import android.util.Log
import com.example.giphy.data.DataOrException
import com.example.giphy.model.trending.response.TrendingResponse
import com.example.giphy.network.HomeAPI
import com.example.giphy.util.Constants
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeAPI) {
    suspend fun getTrendingGif(
        limit: Int,
        offset: Int,
        rating: String,
        random_id: String,
        bundle: String
    ): DataOrException<TrendingResponse, Boolean, Exception> {
        val response = try {
            api.getTrendingGif(
                Constants.API_KEY, limit, offset, rating, random_id, bundle
            )
        } catch (e: Exception){
            if (e == CancellationException())
                Log.d("HomeRepository", "getTrendingGif: $e")
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}