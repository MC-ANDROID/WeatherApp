package com.plcoding.weatherapp.data.remote


data class WeatherDataDto(
    // "time"
    val times: List<String>,
    // "temperature_2m"
    val temperatures: List<Double>,
    // "weather_code"
    val weatherCodes: List<Int>,
    // "pressure_msl"
    val pressures: List<Double>,
    // "wind_speed_10m"
    val windSpeeds: List<Double>,
    // "relative_humidity_2m"
    val humidities: List<Double>
)