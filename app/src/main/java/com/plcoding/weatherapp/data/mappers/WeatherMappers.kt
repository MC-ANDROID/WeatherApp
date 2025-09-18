package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.WeatherDataDto
import com.plcoding.weatherapp.data.remote.WeatherDto
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return times.mapIndexed { i, timeStr ->
        val tem = temperatures[i]
        val code = weatherCodes[i]
        val windSpeed = windSpeeds[i]
        val pressure = pressures[i]
        val humidity = humidities[i]
        i to WeatherData(
            time = java.time.LocalDateTime.parse(timeStr, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = tem,
            weatherType = WeatherType.fromWMO(code),
            windSpeed = windSpeed,
            pressure = pressure,
            humidity = humidity
        )
    }.groupBy { it.first /24 }
        .mapValues { entry ->
            entry.value.map { it.second }
        }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find{
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}