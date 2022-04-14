package com.example.giphy.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun TabBackgroundColor(index: Int) : Brush {
    var brush: Brush = Brush.linearGradient(listOf(Color.White, Color.White))
    when(index % 5){
        0 ->{}
        1 ->{}
        2 ->{}
        3 ->{}
        4 ->{}
    }
    return brush
}