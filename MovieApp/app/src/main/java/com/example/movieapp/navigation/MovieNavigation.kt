package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.Details.DetailsScreen
import com.example.movieapp.screens.Home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController() // controller
    //host
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        //graph
        composable(MovieScreens.HomeScreen.name) {
            // here we pass where this should lead us to
            HomeScreen(navController = navController)
        }
        //               route 시작
        //www.google.com/detail-screen/id=34
        composable(
            MovieScreens.DetailsScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) { backStackEnrty ->
            DetailsScreen(
                navController = navController,
                backStackEnrty.arguments?.getString("movie")
            )
        }
    }
}
























