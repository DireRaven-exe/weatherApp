package com.example.weatherapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlin.math.round


@OptIn(ExperimentalCoilApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastCard(forecast: DailyForecast) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .width(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(data = forecast.weather.firstOrNull()?.iconUrl.orEmpty()),
                    contentDescription = forecast.weather.firstOrNull()?.description.orEmpty(),
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = dateFormat(forecast.dt),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = forecast.weather.firstOrNull()?.description.orEmpty(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${round(forecast.main.temp).toInt()}°C\n",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

//                Text(
//                    text = "${forecast.main.humidity}%\n",
//                    fontSize = 16.sp
//                )
//                Text(
//                    text = "${forecast.wind.speed} m/s\n",
//                    fontSize = 16.sp
//                )
        }
    }
}