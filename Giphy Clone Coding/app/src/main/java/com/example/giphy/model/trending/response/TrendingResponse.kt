package com.example.giphy.model.trending.response

data class TrendingResponse(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)