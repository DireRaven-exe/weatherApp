package com.example.weatherapp.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenWeatherMapService {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private val client = OkHttpClient.Builder().build()

    fun create(): WeatherService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WeatherService::class.java)
    }
}
