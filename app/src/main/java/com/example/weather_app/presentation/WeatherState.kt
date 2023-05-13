package com.example.weather_app.presentation

import com.example.weather_app.domain.weather.WeatherInfo

data class WeatherState(
    val isLoading: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val error: String? = ""
)
