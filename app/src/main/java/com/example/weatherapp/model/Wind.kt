package com.example.weatherapp.model

/**
 *   Класс Wind         - содержит информацию о скорости ветра
 *   @param speed       - скорость ветра в метрах в секунду.
 *   @param deg         - направление ветра в градусах (от 0 до 360);
 */
data class Wind(
    val speed: Double,
    val deg: Double
)
