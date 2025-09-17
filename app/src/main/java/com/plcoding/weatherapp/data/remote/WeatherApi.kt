package com.plcoding.weatherapp.data.remote

interface WeatherApi {

    // /v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m,pressure_msl
    suspend fun getWeatherData(
        lat: Double,
        long: Double
    ): WeatherDto

}