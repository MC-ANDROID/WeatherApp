package com.plcoding.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WeatherViewModel(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel(){

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            locationTracker.getCurrentLocation()?.let { location ->
                when (val weatherInfo =
                    repository.getWeatherInfo(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = weatherInfo.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = weatherInfo.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }?: run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }

}