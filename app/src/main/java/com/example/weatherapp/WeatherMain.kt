package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.ForecastResponse
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.retrofit.getWeatherData
import com.example.weatherapp.weather.WeatherInput
import com.example.weatherapp.weather.WeatherScreen

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherMain(themeState: Any) {
    val context = LocalContext.current
    val isDarkTheme = remember { mutableStateOf(getThemeState(context)) }
    val (weather, setWeather) = remember { mutableStateOf<WeatherResponse?>(null) }
    val (forecast, setForecast) = remember { mutableStateOf<ForecastResponse?>(null) }

    MaterialTheme(colors = if (isDarkTheme.value) darkColors() else lightColors()) {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Weather") },
                        actions = {
                            Switch(
                                checked = isDarkTheme.value,
                                onCheckedChange = { isChecked ->
                                    isDarkTheme.value = isChecked
                                    saveThemeState(context, isChecked)
                                }
                            )
                        }
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                        WeatherInput(onClick = { city ->
                            getWeatherData(city, setWeather, setForecast)
                        })
                        WeatherScreen(weatherResponse = weather, forecastResponse = forecast)
                }
            }
        }
    }
}

