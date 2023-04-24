package com.example.weatherapp.model

/**
 *   Класс MainWeatherData   - содержит информацию о температуре, давлении и влажности.
 *   @param temp             - температура в градусах Цельсия.
 *   @param pressure         - атмосферное давление в гектопаскалях.
 *   @param humidity         - относительная влажность в процентах.
 */
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double
)