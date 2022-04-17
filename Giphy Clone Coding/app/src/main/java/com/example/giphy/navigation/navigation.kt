package com.example.giphy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.giphy.screens.HomeScreen
import com.example.giphy.screens.MyScreen
import com.example.giphy.screens.search.SearchScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    NavHost(navController = navController, startDestination = GiphyScreens.HomeScreen.name) {
        composable(GiphyScreens.HomeScreen.name) {
            HomeScreen(navController = navController, systemUiController)
        }

        composable(GiphyScreens.SearchScreen.name) {
            SearchScreen(navController = navController, systemUiController)
        }

        composable(GiphyScreens.MyScreen.name) {
            MyScreen(navController = navController, systemUiController)
        }
    }
}