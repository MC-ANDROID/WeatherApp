package com.plcoding.weatherapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single


@Single
fun provideHttpClient(): HttpClient = HttpClient(CIO){
    defaultRequest {
        url("https://api.open-meteo.com/")
    }
    install(ContentNegotiation){
        json(Json{
          ignoreUnknownKeys = true
        })
    }
}

@Single
class WeatherApiImpl(
    private val client: HttpClient
) : WeatherApi {
    override suspend fun getWeatherData(
        lat: Double,
        long: Double
    ) = client.get {
        url {
            path("v1/forecast")
            parameters.append("latitude", lat.toString())
            parameters.append("longitude", long.toString())
            parameters.append(
                "hourly",
                "temperature_2m,relative_humidity_2m,windspeed_10m,pressure_msl,weather_code"
            )
        }
    }.body<WeatherDto>()
}