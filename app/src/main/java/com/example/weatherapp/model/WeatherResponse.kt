package com.example.weatherapp.model

/**
 *   WeatherResponse        - data class, в котором хранится информация о погоде
 *   @param coord           - координаты по долготе и широте
 *   @param weather         - это массив объектов, каждый из которых содержит информацию о погодных условиях в данном городе в данный момент.
 *   @param main            - температура и влажность
 *   @param wind            - данные о ветре
 *   @param clouds          - облака
 *   @param dt              - дата прогонза
 *   @param sys             - содержит информацию о системе, в которой запрошен прогноз погоды
 *   @param timezone        - часовой пояс
 *   @param id              - идентификатор города
 *   @param name            - название города.
 *   @param cod             - запроса (200 успешный, остальное нет)
 */
data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long, // дата прогноза
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String, // название города
    val cod: Int
)

