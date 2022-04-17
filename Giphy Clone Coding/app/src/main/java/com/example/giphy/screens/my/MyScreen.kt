package com.example.giphy.screens

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.giphy.R
import com.example.giphy.components.*
import com.example.giphy.navigation.GiphyScreens
import com.example.giphy.ui.theme.BackgroundColorDark
import com.example.giphy.ui.theme.MyPageTopBarBackgroundColor
import com.example.giphy.util.Constants
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController


@OptIn(com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun MyScreen(navController: NavController, systemUiController: SystemUiController) {
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = BackgroundColorDark,
            darkIcons = false
        )
    }

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val pagerState = rememberPagerState()

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    Scaffold(
        bottomBar = {
            GiphyBottomAppBar(navController, GiphyScreens.MyScreen.name)
        }
    ) { paddingValues ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                AsyncImage(
                    model = R.drawable.ic_mypage_top_background,
                    contentDescription = "background",
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((configuration.screenHeightDp * 0.13).dp)
                        .background(Brush.verticalGradient(MyPageTopBarBackgroundColor)),
                    contentScale = ContentScale.Crop
                )

                AsyncImage(
                    model = R.drawable.ic_my_profile,
                    contentDescription = "profile",
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .offset(
                            ((configuration.screenWidthDp / 2) - 35).dp,
                            45.dp
                        )
                        .padding(top = 30.dp)
                        .size(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = "MEIMPACT",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomMyTabRow(
                pagerState,
                Constants.MY_PAGE_LIST,
                configuration
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalPager(
                count = Constants.MY_PAGE_LIST.size,
                state = pagerState
            ) { index ->
                when (index) {
                    0 -> FavoritesItems()
                    1 -> UploadsItems()
                    2 -> CollectionsItems()
                }
            }
        }

    }
}