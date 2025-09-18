package com.plcoding.weatherapp.data.repository

import com.plcoding.weatherapp.data.mappers.toWeatherInfo
import com.plcoding.weatherapp.data.remote.WeatherApi
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import org.koin.core.annotation.Single

@Single
class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherInfo(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val weatherDto = weatherApi.getWeatherData(
                lat = lat,
                long = long
            )
            Resource.Success(weatherDto.toWeatherInfo())
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message?:"An unknown error occurred.")
        }
    }
}