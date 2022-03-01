package com.example.areader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.areader.screens.details.BookDetailsScreen
import com.example.areader.screens.home.ReaderHomeScreen
import com.example.areader.screens.login.ReaderLoginScreen
import com.example.areader.screens.search.ReaderSearchScreen
import com.example.areader.screens.splash.ReaderSplashScreen
import com.example.areader.screens.stats.ReaderStatsScreen
import com.example.areader.screens.update.BookUpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {
        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            ReaderHomeScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {
            ReaderStatsScreen(navController = navController)
        }

        composable(ReaderScreens.BookSearchScreen.name) {
            ReaderSearchScreen(navController = navController)
        }

        composable(ReaderScreens.BookUpdateScreen.name) {
            BookUpdateScreen(navController = navController)
        }

        composable(ReaderScreens.BookDetailScreen.name) {
            BookDetailsScreen(navController = navController)
        }
    }
}