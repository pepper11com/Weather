package com.example.weather_app.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.R
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {

    state.weatherInfo?.currentWeatherData?.let { weatherInfo ->
        Card(
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${weatherInfo.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Image(
                    painter = painterResource(id = weatherInfo.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp)
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Text(
                    text = "${weatherInfo.temperatureCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Text(
                    text = weatherInfo.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.padding(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = weatherInfo.pressure.roundToInt(),
                        unit = "hPa",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )

                    WeatherDataDisplay(
                        value = weatherInfo.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )

                    WeatherDataDisplay(
                        value = weatherInfo.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}