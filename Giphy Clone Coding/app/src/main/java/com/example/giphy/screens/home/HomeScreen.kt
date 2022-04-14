package com.example.giphy.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.giphy.components.TopAppBarTitle
import com.example.giphy.screens.home.HomeViewModel
import com.example.giphy.ui.theme.BackgroundColorDark
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    systemUiController: SystemUiController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = BackgroundColorDark,
            darkIcons = false
        )
    }

    val configuration = LocalConfiguration.current

    val toolbarHeight = 48.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    var totalScrollOffsetPx = remember { 0f }
    var toolbarOffsetHeightPx by remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                totalScrollOffsetPx = (totalScrollOffsetPx + consumed.y)
                Log.d(
                    "TAG",
                    "onPreScroll: totalScrollOffsetPx $totalScrollOffsetPx / current available ${available.y}"
                )

                toolbarOffsetHeightPx = totalScrollOffsetPx.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }


    var selectedIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState()

    val pages = listOf(
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

    Box(
        Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        TopAppBar(
            modifier = Modifier
                .height(toolbarHeight)
                .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.roundToInt()) }
                .background(BackgroundColorDark),
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) { TopAppBarTitle(configuration = configuration) }

        Column(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = 0,
                        y = toolbarOffsetHeightPx.roundToInt() + toolbarHeight.toPx().toInt()
                    )
                }
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedIndex,
                modifier = Modifier
                    .wrapContentWidth()
                    .height((configuration.screenHeightDp * 0.05).dp),
                backgroundColor = BackgroundColorDark,
                edgePadding = 0.dp,
                indicator = { tabPositions: List<TabPosition> ->
                    Surface(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedIndex]),
                        shape = CircleShape,
                        color = Color.Transparent
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        ) {}
                    }
                },
                divider = {}
            ) {
                pages.forEachIndexed { index, pages ->
                    Tab(
                        text = { Text(text = pages, color = Color.Blue) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        modifier = Modifier
                            .zIndex(if (selectedIndex == index) 1f else 0f),
                        unselectedContentColor = Color.White
                    )
                }
            }

            val lazyPagingItems = homeViewModel.samplePagingFlow.collectAsLazyPagingItems()

            LazyColumn() {
                items(lazyPagingItems) { sample ->
                    Text(
                        sample ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }

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

//            LazyVerticalGrid(columns = GridCells.Adaptive(50.dp)) {
//                items(photos) { photo ->
//                    AsyncImage(model = photo, contentDescription = "")
//                }
//            }
        }


    }
}