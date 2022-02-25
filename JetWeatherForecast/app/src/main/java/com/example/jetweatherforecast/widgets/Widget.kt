package com.example.jetweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.model.WeatherItem
import com.example.jetweatherforecast.util.formatDate
import com.example.jetweatherforecast.util.formatDateTime
import com.example.jetweatherforecast.util.formatDecimals

@Composable
fun WeatherCard(
    dayWeather: WeatherItem,
    imageUrl: String
) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDate(dayWeather.dt).split(",")[0],
                modifier = Modifier.padding(start = 5.dp),
                style = MaterialTheme.typography.body1,
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = dayWeather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold,
                    )
                ) {
                    append(formatDecimals(dayWeather.temp.max) + "º")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray
                    )
                ) {
                    append(formatDecimals(dayWeather.temp.min) + "º")
                }

            })
        }
    }

//    Row(
//        modifier = Modifier
//            .padding(3.dp)
//            .clip(
//                RoundedCornerShape(
//                    topStartPercent = 50,
//                    bottomStartPercent = 50, bottomEndPercent = 50
//                )
//            )
//            .background(Color.White)
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(
//            text = formatDateWeek(dayWeather.dt),
//            modifier = Modifier.padding(5.dp),
//            style = MaterialTheme.typography.body1,
//            fontWeight = FontWeight.SemiBold,
//        )
//
//        Image(
//            painter = rememberImagePainter(imageUrl),
//            contentDescription = "weather icon",
//            modifier = Modifier.size(50.dp)
//        )
//
//        Text(
//            text = dayWeather.weather[0].description,
//            modifier = Modifier
//                .clip(RoundedCornerShape(15.dp))
//                .background(Color(0xFFFFC400))
//                .padding(5.dp),
//            style = MaterialTheme.typography.body2,
//        )
//
//        Row(modifier = Modifier.padding(5.dp)) {
//            Text(
//                text = dayWeather.temp.max.toInt().toString() + "º",
//                color = Color(0xFF393BFC),
//                style = MaterialTheme.typography.body1,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.width(5.dp))
//            Text(
//                text = dayWeather.temp.min.toInt().toString() + "º",
//                color = Color(0xFFB6B6B6),
//                style = MaterialTheme.typography.body1,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
}

@Composable
fun SunriseSunsetTimeRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise icon",
                modifier = Modifier.size(30.dp)
            )
            Text(text = formatDateTime(weather.sunrise), style = MaterialTheme.typography.caption)
        }
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = formatDateTime(weather.sunset), style = MaterialTheme.typography.caption)
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem, isImperial: Boolean) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.humidity}%", style = MaterialTheme.typography.caption)
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.pressure} psi", style = MaterialTheme.typography.caption)
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${formatDecimals(weather.speed)} mph" + if (isImperial) "mph" else "m/s",
                style = MaterialTheme.typography.caption
            )
        }
    }

}


@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl) {

        },
        contentDescription = "Icon Image",
        modifier = Modifier.size(80.dp)
    )
}