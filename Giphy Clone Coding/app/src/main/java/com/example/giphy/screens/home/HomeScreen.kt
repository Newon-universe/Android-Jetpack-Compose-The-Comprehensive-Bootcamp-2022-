package com.example.giphy.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.giphy.components.*
import com.example.giphy.navigation.GiphyScreens
import com.example.giphy.ui.theme.BackgroundColorDark
import com.example.giphy.util.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    systemUiController: SystemUiController,
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

    val pagerState = rememberPagerState()

    Scaffold(
        bottomBar = {
            GiphyBottomAppBar(navController, GiphyScreens.HomeScreen.name)
        }
    ) { PaddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            GiphyTopAppBar(toolbarHeight, toolbarOffsetHeightPx, configuration)

            Column(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = 0,
                            y = toolbarOffsetHeightPx.roundToInt() + toolbarHeight.toPx().toInt()
                        )
                    }
            ) {
                CustomHomeTabRow(
                    pagerState,
                    Constants.HOME_PAGE_LIST,
                    configuration
                )

                Spacer(Modifier.height(10.dp))

                HorizontalPager(
                    count = Constants.HOME_PAGE_LIST.size,
                    state = pagerState
                ) { index ->
                    when (index) {
                        0 -> TrendingItems()
                        1 -> ArtistsItems()
                        2 -> ClipsItems()
                        3 -> StoriesItems()
                        4 -> StickersItems()
                        5 -> EmojiItems()
                        6 -> TextItems()
                        7 -> ReactionsItems()
                        8 -> MemesItems()
                        9 -> CatsItems()
                        10 -> DogsItems()
                    }
                }
            }
        }
    }

}

