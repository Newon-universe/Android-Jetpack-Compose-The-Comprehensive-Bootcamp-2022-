package com.example.giphy.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.giphy.R
import com.example.giphy.ui.theme.BackgroundColorDark

@Composable
fun TopAppBarTitle(configuration: Configuration) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.giphy_transparent_180px),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomVerticalGridColumn(scrollState: LazyListState) {
    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxSize()
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundColorDark)
            ) {
                Text(text = "I am Sticky Header")
            }
        }

        item {
            Text(text = "Hi")
        }

        items(50) {
            Text(text = "$it")
        }
    }
}