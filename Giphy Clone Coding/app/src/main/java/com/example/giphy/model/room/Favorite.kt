package com.example.giphy.model.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tbl")
data class Favorite(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "url_id")
    val urlId: String,

    @ColumnInfo(name = "width")
    val width: Double,

    @ColumnInfo(name = "height")
    val height: Double
)