package com.example.weatherapp.model

import java.text.SimpleDateFormat
import java.util.*


data class DailyForecast(
    val dt: Long, // дата прогноза
    val main: Main, // содержит информацию о температуре, давлении и влажности.
    val weather: List<Weather>, // погода основная инфа
    val clouds: Clouds,
    val wind: Wind, // информация о ветре
    val sys: Sys,
)



fun dateFormat(dt: Long): String {
    val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    val dateTime = Date(dt * 1000L)
    return dateFormatter.format(dateTime)
}