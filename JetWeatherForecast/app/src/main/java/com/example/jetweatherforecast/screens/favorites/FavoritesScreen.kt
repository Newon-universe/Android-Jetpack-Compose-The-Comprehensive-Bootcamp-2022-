package com.example.jetweatherforecast.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforecast.model.Favorite
import com.example.jetweatherforecast.navigation.WeatherScreens
import com.example.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favorite Cities",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController
        ) { navController.popBackStack() }
    }) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = favoritesViewModel.favList.collectAsState().value

                LazyColumn {
                    items(items = list) {
                        CityRow(
                            it,
                            navController = navController,
                            favoritesViewModel = favoritesViewModel
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun CityRow(
    favorite: Favorite,
    navController: NavController,
    favoritesViewModel: FavoritesViewModel
) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favorite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xFFB2DFDB)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = favorite.city, modifier = Modifier.padding(start = 4.dp))
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFD1E3E1)
            ) {
                Text(
                    text = favorite.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Icon(
                imageVector = Icons.Rounded.Delete, contentDescription = "Delete Icon",
                modifier = Modifier.clickable {
                    favoritesViewModel.deleteFavorite(favorite = favorite)
                },
                tint = Color.Red.copy(alpha = 0.3f)
            )
        }
    }
}
