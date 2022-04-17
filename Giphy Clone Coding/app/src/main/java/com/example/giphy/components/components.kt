package com.example.giphy.components

import LazyStaggeredGrid
import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.giphy.R
import com.example.giphy.model.room.Favorite
import com.example.giphy.navigation.GiphyScreens
import com.example.giphy.screens.home.HomeViewModel
import com.example.giphy.screens.my.MyViewModel
import com.example.giphy.ui.theme.*
import com.example.giphy.util.CalculateScaledSize
import com.example.giphy.util.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import items
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun GiphyTopAppBar(
    toolbarHeight: Dp,
    toolbarOffsetHeightPx: Float,
    configuration: Configuration
) {
    TopAppBar(
        modifier = Modifier
            .height(toolbarHeight)
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.roundToInt()) }
            .background(BackgroundColorDark),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) { TopAppBarTitle(configuration = configuration) }
}

@Composable
fun TopAppBarTitle(configuration: Configuration) {
    Row(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.giphy_logo_transparent_180px),
            contentDescription = "Giphy Logo",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(
                    (configuration.screenWidthDp * 0.33).dp,
                    (configuration.screenHeightDp * 0.05).dp
                ),
        )

        Row {
            Text(
                text = "Create",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}


@Composable
fun GiphyBottomAppBar(
    navController: NavController,
    currentScreens: String
) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .background(BottomBarBackgroundColor),
    ) {
        BottomBarItem(
            navController = navController,
            currentScreens = currentScreens,
            buttonName = GiphyScreens.HomeScreen.name,
            selectAction = {

            }
        )
        BottomBarItem(
            navController = navController,
            currentScreens = currentScreens,
            buttonName = GiphyScreens.SearchScreen.name,
            selectAction = {

            }
        )
        BottomBarItem(
            navController = navController,
            currentScreens = currentScreens,
            buttonName = GiphyScreens.MyScreen.name,
            selectAction = {

            }
        )
    }
}

@Composable
fun RowScope.BottomBarItem(
    navController: NavController,
    currentScreens: String,
    buttonName: String,
    selectAction: (String) -> Unit
) {
    BottomNavigationItem(
        selected = currentScreens == buttonName,
        onClick = { selectAction(currentScreens) },
        enabled = false,
        icon = {
            BottomBarIcon(navController, currentScreens, buttonName)
        },
        modifier = Modifier.background(Color.Transparent)
    )
}

@Composable
private fun BottomBarIcon(
    navController: NavController,
    currentScreens: String,
    buttonName: String
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            when (buttonName) {
                GiphyScreens.HomeScreen.name -> R.raw.ic_home
                GiphyScreens.SearchScreen.name -> R.raw.ic_search
                GiphyScreens.MyScreen.name -> R.raw.ic_my
                else -> R.raw.ic_home
            }
        )
    )

    val color = when (currentScreens) {
        GiphyScreens.HomeScreen.name -> TabIndicatorColorHome
        GiphyScreens.SearchScreen.name -> TabIndicatorColorSearch
        GiphyScreens.MyScreen.name -> TabIndicatorColorMy
        else -> Color.Transparent
    }

    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            {
                navController.navigate(buttonName) {
                    popUpTo(currentScreens) { inclusive = true }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LottieAnimation(
            composition = composition,
            isPlaying = currentScreens == buttonName,
            restartOnPlay = false,
            modifier = Modifier
                .size(if (buttonName == GiphyScreens.HomeScreen.name) 40.dp else 50.dp)
                .background(Color.Transparent)
        )

        if (currentScreens == buttonName)
            AnimatedVisibility(
                visible = currentScreens == buttonName,
                enter = fadeIn(),
            ) {
                Surface(
                    shape = CircleShape,
                    modifier = Modifier.size(10.dp),
                    color = color
                ) {}
            }
        else
            Spacer(modifier = Modifier.height(10.dp))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomHomeTabRow(
    pagerState: PagerState,
    pages: List<String>,
    configuration: Configuration,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .wrapContentWidth()
            .height((configuration.screenHeightDp * 0.05).dp),
        backgroundColor = TabRowColor,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 0.dp,
                color = TabRowColor
            )
            TabRowDefaults.Divider(color = Color.Transparent)
        },
        divider = {}
    ) {
        pages.forEachIndexed { index, pages ->
            Surface(
                shape = CircleShape,
                color = Color.Transparent
            ) {
                Tab(
                    text = {
                        Text(
                            text = pages,
                            color = if (pagerState.currentPage == index) TabColorTextSelected else TabColorTextUnselected
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch { pagerState.scrollToPage(index) }
                    },
                    modifier = Modifier
                        .zIndex(if (pagerState.currentPage == index) 1f else 0f)
                        .background(
                            brush =
                            if (pagerState.currentPage == index)
                                homeViewModel.getTabColor(index)
                            else Brush.linearGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Transparent
                                )
                            )
                        ),
                    unselectedContentColor = Color.White
                )
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomMyTabRow(
    pagerState: PagerState,
    pages: List<String>,
    configuration: Configuration,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .wrapContentWidth()
            .height((configuration.screenHeightDp * 0.05).dp),
        backgroundColor = TabRowColor,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 0.dp,
                color = TabRowColor
            )
            TabRowDefaults.Divider(color = Color.Transparent)
        },
        divider = {}
    ) {
        pages.forEachIndexed { index, pages ->
            Surface(
                shape = CircleShape,
                color = Color.Transparent
            ) {
                Tab(
                    text = {
                        Text(
                            text = pages,
                            color = if (pagerState.currentPage == index) TabColorTextSelected else TabColorTextUnselected
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch { pagerState.scrollToPage(index) }
                    },
                    modifier = Modifier
                        .zIndex(if (pagerState.currentPage == index) 1f else 0f)
                        .background(
                            brush =
                            if (pagerState.currentPage == index)
                                homeViewModel.getTabColor(index)
                            else Brush.linearGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Transparent
                                )
                            )
                        ),
                    unselectedContentColor = Color.White
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingItems(
    homeViewModel: HomeViewModel = hiltViewModel(),
    myViewModel: MyViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val favoriteList = myViewModel.favList.collectAsState().value

    val pagingData = homeViewModel.getPager(
        20,
        Constants.RATING_LIST[0],
        Constants.RANDOM_ID,
        Constants.BUNDLE_LIST[1]
    ).collectAsLazyPagingItems()

    LazyStaggeredGrid(columnCount = 2) {
        items(pagingData.itemSnapshotList) { item ->

            item?.let {
                val size = with(LocalDensity.current) {
                    CalculateScaledSize(
                        context,
                        item.images.fixed_width.width.toDouble(),
                        item.images.fixed_width.height.toDouble()
                    ).run { DpSize(width.toDp(), height.toDp()) }
                }

                var isFavorite by remember {
                    mutableStateOf(
                        favoriteList.contains(
                            Favorite(
                                urlId = item.id,
                                width = item.images.fixed_width.width.toDouble(),
                                height = item.images.fixed_width.height.toDouble()
                            )
                        )
                    )
                }

                Surface(
                    shape = RoundedCornerShape(5)
                ) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        AsyncImage(
                            model = "${Constants.IMAGE_BASE_URL}${item.id}${Constants.IMAGE_OPTION}",
                            contentDescription = "",
                            imageLoader = imageLoader,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(1.8.dp)
                                .size(size)
                        )

                        Surface(
                            shape = RoundedCornerShape(10),
                            color = BottomBarBackgroundColor
                        ) {
                            IconToggleButton(
                                checked = isFavorite,
                                onCheckedChange = {
                                    isFavorite = !isFavorite

                                    if (isFavorite)
                                        myViewModel.insertFavorite(
                                            favorite = Favorite(
                                                urlId = item.id,
                                                width = item.images.fixed_width.width.toDouble(),
                                                height = item.images.fixed_width.height.toDouble()
                                            )
                                        )
                                    else
                                        myViewModel.deleteFavorite(
                                            favorite = Favorite(
                                                urlId = item.id,
                                                width = item.images.fixed_width.width.toDouble(),
                                                height = item.images.fixed_width.height.toDouble()
                                            )
                                        )
                                },
                                modifier = Modifier.background(BottomBarBackgroundColor)
                            ) {
                                Icon(
                                    painter = painterResource(id = if (isFavorite) R.drawable.ic_like_on else R.drawable.ic_like_off),
                                    contentDescription = if (isFavorite) "좋아요" else "좋아요 취소",
                                    tint = LikeColor
                                )
                            }
                        }

                    }
                }
            }
        }
    }


}

@Composable
fun ArtistsItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "ArtistsItems", color = Color.White)
        }
    }
}

@Composable
fun ClipsItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "ClipsItems", color = Color.White)
        }
    }
}

@Composable
fun StoriesItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "StoriesItems", color = Color.White)
        }
    }
}

@Composable
fun StickersItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "StickersItems", color = Color.White)
        }
    }
}

@Composable
fun EmojiItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "EmojiItems", color = Color.White)
        }
    }
}

@Composable
fun TextItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "TextItems", color = Color.White)
        }
    }
}

@Composable
fun ReactionsItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "ReactionsItems", color = Color.White)
        }
    }
}

@Composable
fun MemesItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "MemesItems", color = Color.White)
        }
    }
}

@Composable
fun CatsItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "CatsItems", color = Color.White)
        }
    }
}

@Composable
fun DogsItems() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "DogsItems", color = Color.White)
        }
    }
}

@Composable
fun FavoritesItems(
    myViewModel: MyViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val favoriteList = myViewModel.favList.collectAsState().value

    LazyStaggeredGrid(columnCount = 2) {
        items(favoriteList) { item ->
            item.let {
                val size = with(LocalDensity.current) {
                    CalculateScaledSize(
                        context,
                        item.width,
                        item.height
                    ).run { DpSize(width.toDp(), height.toDp()) }
                }

                Surface(
                    shape = RoundedCornerShape(5)
                ) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        AsyncImage(
                            model = "${Constants.IMAGE_BASE_URL}${item.urlId}${Constants.IMAGE_OPTION}",
                            contentDescription = "",
                            imageLoader = imageLoader,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(1.8.dp)
                                .size(size)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UploadsItems() {

}

@Composable
fun CollectionsItems() {

}