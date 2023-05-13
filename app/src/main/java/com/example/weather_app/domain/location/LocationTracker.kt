package com.example.weather_app.domain.location

import android.location.Location

// for kmm this doesn't work because its from the android sdk

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}