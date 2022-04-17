package com.example.giphy.util

object Constants {
    const val BASE_URL = "http://api.giphy.com/v1/"
    const val API_KEY = "K1nFx9tctnFGjoTvuDPT7loB9nDQwGdB"
    val RATING_LIST = listOf("g", "pg", "pg-13", "r")
    const val RANDOM_ID = "e826c9fc5c929e0d6c6d423841a282aa"
    val BUNDLE_LIST =
        listOf("clips_grid_picker", "messaging_non_clips", "sticker_layering", "low_bandwidth")
    const val IMAGE_BASE_URL = "https://i.giphy.com/media/"
    const val IMAGE_OPTION = "/giphy.webp"
    val HOME_PAGE_LIST = listOf(
        "Trending",
        "Artists",
        "Clips",
        "Stories",
        "Stickers",
        "Emoji",
        "Text",
        "Reactions",
        "Memes",
        "Cats",
        "Dogs"
    )
    val MY_PAGE_LIST = listOf(
        "Favorites",
        "Uploads",
        "Collections"
    )
}