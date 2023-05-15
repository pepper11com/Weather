package com.example.weather_app.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.domain.location.LocationTracker
import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set


    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            locationTracker.getCurrentLocation()?.let { location ->
                state = when (val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state.copy(isLoading = false, weatherInfo = result.data, error = null)
                    }

                    is Resource.Error -> {
                        state.copy(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred",
                            weatherInfo = null
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Unable to get location, please make sure that location permissions are granted and that GPS is enabled"
                )
            }
        }
    }

}