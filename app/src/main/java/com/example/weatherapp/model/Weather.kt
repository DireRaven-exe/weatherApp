package com.example.weatherapp.model

/**
 *  Weather             - отображает описание для погоды
 *  @param id           - уникальный идентификатор погодных условий.
 *  @param main         - общая группа погодных условий (дождь, снег, облачно и т.д.).
 *  @param description  - более подробное описание погодных условий.
 *  @param icon         - идентификатор иконки, которая отображает текущие погодные условия.
 */
data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
) {
    val iconUrl: String
        get() = "https://openweathermap.org/img/w/$icon.png"
}