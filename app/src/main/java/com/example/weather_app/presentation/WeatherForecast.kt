package com.example.weather_app.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { weatherData ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(16.dp))

            LazyRow(content = {
                items(weatherData) { data ->
                    HourlyWeatherDisplay(
                        weatherData = data,
                        modifier = Modifier.height(100.dp).padding(horizontal = 16.dp)
                    )
                }
            })
        }
    }
}