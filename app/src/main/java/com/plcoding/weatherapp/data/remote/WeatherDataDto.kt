package com.plcoding.weatherapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataDto(
    @SerialName("time")
    val times: List<String>,
    @SerialName("temperature_2m")
    val temperatures: List<Double>,
    @SerialName("weather_code")
    val weatherCodes: List<Int>,
    @SerialName("pressure_msl")
    val pressures: List<Double>,
    @SerialName("windspeed_10m")
    val windSpeeds: List<Double>,
    @SerialName("relative_humidity_2m")
    val humidities: List<Double>
)