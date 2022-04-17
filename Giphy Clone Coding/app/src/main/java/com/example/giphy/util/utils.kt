package com.example.giphy.util

import android.content.Context
import android.media.Image
import android.util.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.ceil
import kotlin.math.roundToInt

val photos = listOf(
    "https://cdn.pixabay.com/photo/2015/07/28/22/12/autumn-865157_960_720.jpg",
    "https://cdn.pixabay.com/photo/2017/06/07/10/47/elephant-2380009_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/07/28/22/11/wheat-865152_960_720.jpg",
    "https://cdn.pixabay.com/photo/2020/07/09/03/41/duck-5385741_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/07/28/22/12/autumn-865157_960_720.jpg",
    "https://cdn.pixabay.com/photo/2017/06/07/10/47/elephant-2380009_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/07/28/22/11/wheat-865152_960_720.jpg",
    "https://cdn.pixabay.com/photo/2020/07/09/03/41/duck-5385741_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/07/28/22/12/autumn-865157_960_720.jpg",
    "https://cdn.pixabay.com/photo/2017/06/07/10/47/elephant-2380009_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/07/28/22/11/wheat-865152_960_720.jpg",
    "https://cdn.pixabay.com/photo/2020/07/09/03/41/duck-5385741_960_720.jpg"
)

fun CalculateScaledSize(context: Context, width: Double, height: Double): Size {
    val displayWidth = context.resources.displayMetrics.widthPixels
    val columnWidth = (displayWidth / 2.toDouble()).roundToInt()
    val scale = columnWidth / width.toDouble()
    return Size(columnWidth, (scale * height).roundToInt())
}