package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let{ data ->
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow {
                items(data){ hourData ->
                    HourlyWeatherDisplay(
                        weatherData = hourData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    }
}