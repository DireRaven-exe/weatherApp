package com.example.weatherapp.retrofit

import android.util.Log
import com.example.weatherapp.model.ForecastResponse
import com.example.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Call<WeatherResponse>

    @GET("forecast")
    fun getForecastData(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Call<ForecastResponse>
}

fun getWeatherData(
    city: String,
    setWeather: (WeatherResponse?) -> Unit,
    setForecast: (ForecastResponse?) -> Unit
) {
    val apiKey = "2cdf7dfb7f7f321ac3b65f84e3710703"
    val units = "metric"
    val weatherService = OpenWeatherMapService.create()

    val weatherCall = weatherService.getWeatherData(city, apiKey, units)
    weatherCall.enqueue(object : Callback<WeatherResponse> {
        override fun onResponse(
            call: Call<WeatherResponse>,
            response: Response<WeatherResponse>
        ) {
            val weatherResponse = response.body()
            setWeather(weatherResponse)
        }

        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            Log.e("MainActivity", t.message ?: "Unknown error occurred")
        }
    })

    val forecastCall = weatherService.getForecastData(city, apiKey, units)
    forecastCall.enqueue(object : Callback<ForecastResponse> {
        override fun onResponse(
            call: Call<ForecastResponse>,
            response: Response<ForecastResponse>
        ) {
            val forecastResponse = response.body()
            setForecast(forecastResponse)
        }

        override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
            Log.e("MainActivity", t.message ?: "Unknown error occurred")
        }
    })
}

