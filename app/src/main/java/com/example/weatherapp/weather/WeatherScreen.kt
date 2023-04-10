package com.example.weatherapp.weather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.helper.widget.*
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.weatherapp.model.ForecastCard
import com.example.weatherapp.model.ForecastResponse
import com.example.weatherapp.model.WeatherInfoCard
import com.example.weatherapp.model.WeatherResponse
import kotlin.math.round

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    weatherResponse: WeatherResponse?,
    forecastResponse: ForecastResponse?) {
    if (weatherResponse == null || forecastResponse == null) {
        // Выводим индикатор загрузки
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .padding(top = 16.dp)
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    CurrentTempCard(weatherResponse = weatherResponse)
                    CurrentTempDataCard(weatherResponse = weatherResponse)
                    TomorrowTempDataCard(forecastResponse = forecastResponse)
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CurrentTempCard(weatherResponse: WeatherResponse?) {
    Text(
        text = "Right now",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    )
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Текстовые данные текущей погоды
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = rememberImagePainter(
                    data = weatherResponse?.weather?.firstOrNull()?.iconUrl.orEmpty(),
                    builder = {
                        placeholder(R.drawable.ic_placeholder)
                        error(R.drawable.ic_error)
                    }

                ),
                contentDescription = weatherResponse?.weather?.firstOrNull()?.description.orEmpty()
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                // Текст над картинкой
                Text(
                    text = "${round(weatherResponse?.main!!.temp).toInt()}°C",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                Text(
                    text = "Feels like ${round(weatherResponse.main.feels_like).toInt()}°C",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun CurrentTempDataCard(weatherResponse: WeatherResponse?) {
    WeatherInfoCard(
        label = "Humidity",
        value = "${weatherResponse!!.main.humidity}%",
        modifier = Modifier.padding(top = 8.dp)
    )
    WeatherInfoCard(
        label = "Wind speed",
        value = "${weatherResponse.wind.speed} m/s",
        modifier = Modifier.padding(top = 8.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TomorrowTempDataCard(forecastResponse: ForecastResponse?) {
    // Текстовые данные прогноза на ближайшие дни
    Text(
        text = "this afternoon",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    )
    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            itemsIndexed(forecastResponse!!.list.take(5)) { _, forecast ->
                ForecastCard(forecast = forecast)
            }
                //pageHorizontal()
        }
    )

}