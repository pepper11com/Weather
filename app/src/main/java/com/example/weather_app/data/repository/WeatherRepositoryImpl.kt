package com.example.weather_app.data.repository

import com.example.weather_app.data.mappers.toWeatherInfo
import com.example.weather_app.data.remote.WeatherApi
import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.domain.util.Resource
import com.example.weather_app.domain.weather.WeatherInfo
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(api.getWeatherData(lat, long).toWeatherInfo())
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}