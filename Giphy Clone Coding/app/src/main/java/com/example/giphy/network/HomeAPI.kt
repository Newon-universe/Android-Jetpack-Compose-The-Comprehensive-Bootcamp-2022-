package com.example.giphy.network

import com.example.giphy.model.trending.response.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeAPI {
    @GET("gifs/trending")
    suspend fun getTrendingGif(
        @Query("api_key") api_key: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("random_id") random_id: String,
        @Query("bundle") bundle: String,
    ) : TrendingResponse
}