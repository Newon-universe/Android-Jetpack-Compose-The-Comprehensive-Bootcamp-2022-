package com.example.practice_piece_calculation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_piece_calculation.components.InputField
import com.example.practice_piece_calculation.ui.theme.Practice_piece_calculationTheme

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practice_piece_calculationTheme {
                // A surface container using the 'background' color from the theme
                MyApp(modifier = Modifier) {

                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun MyApp(modifier: Modifier, content: () -> Unit) {
    Surface(modifier = modifier) {
        content
    }
}


@Preview
@ExperimentalComposeUiApi
@Composable
fun Header(modifier: Modifier = Modifier.padding(3.dp)) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.background),
        elevation = 5.dp
    ) {
        Column(
            modifier = modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "코스트 계산기",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "지금 코스트가 나올 확률은",
                modifier = modifier.padding(top = 5.dp, start = 2.dp)
            )
            Spacer(modifier = modifier.height(30.dp))
            Box(
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "00.00%",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.subtitle1
                )
            }
            Spacer(modifier = modifier.height(20.dp))
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val costState = remember {
        mutableStateOf("1")
    }
    val validCostState = remember {
        mutableStateOf(costState.value.trim().toInt())
    }

    val keyboardController = LocalSoftwareKeyboardController.current


    Card(modifier) {
        InputField(
            valueState = costState,
            labelId = "Cost",
            modifier = modifier,
            isSingleLine = true,
            keyboardType = KeyboardType.Number,
            onAction = KeyboardActions {
                if (costState.value.isEmpty()) return@KeyboardActions

                keyboardController?.hide()
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Practice_piece_calculationTheme {

    }
}