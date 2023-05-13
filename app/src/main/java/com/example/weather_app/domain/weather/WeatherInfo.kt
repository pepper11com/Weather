package com.example.weather_app.domain.weather

data class WeatherInfo(
    //map int 0 is today, 1 is tomorrow, etc
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
