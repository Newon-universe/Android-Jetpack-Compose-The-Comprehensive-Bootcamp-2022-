package com.example.giphy.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavController
import com.example.giphy.components.GiphyBottomAppBar
import com.example.giphy.navigation.GiphyScreens
import com.example.giphy.ui.theme.BackgroundColorDark
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun SearchScreen(navController: NavController, systemUiController: SystemUiController) {
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = BackgroundColorDark,
            darkIcons = true
        )
    }

    Scaffold(
        bottomBar = {
            GiphyBottomAppBar(navController, GiphyScreens.SearchScreen.name)
        }
    ) { paddingValues ->

    }
}