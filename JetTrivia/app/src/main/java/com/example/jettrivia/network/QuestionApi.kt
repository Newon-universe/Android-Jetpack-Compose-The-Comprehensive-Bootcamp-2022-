package com.example.jettrivia.network

import com.example.jettrivia.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

// interface -> no need a body, want to make it as class level
@Singleton
interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestion(): Question
}