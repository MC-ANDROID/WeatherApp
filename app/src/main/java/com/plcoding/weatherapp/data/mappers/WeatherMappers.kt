package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.WeatherDataDto
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherType
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