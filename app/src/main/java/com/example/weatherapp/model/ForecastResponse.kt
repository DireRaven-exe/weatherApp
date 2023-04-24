package com.example.weatherapp.model

data class ForecastResponse(
    val list: List<DailyForecast>,
    val city: City
)

data class City(
    val id: Long,
    val name: String,
    val country: String,
    val population: Long,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)