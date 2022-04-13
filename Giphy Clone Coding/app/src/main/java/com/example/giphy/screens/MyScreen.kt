package com.example.giphy.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.giphy.ui.theme.BackgroundColorDark
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun MyScreen(navController: NavController, systemUiController: SystemUiController) {
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = BackgroundColorDark,
            darkIcons = true
        )
    }
}