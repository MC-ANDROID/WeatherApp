package com.plcoding.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.plcoding.weatherapp.data.remote.WeatherApi
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    val appName by inject<String>(named("app_name"))

    // TODO: move these away from the UI laYer
    val httpClient by inject<HttpClient>()
    val weatherApi by inject<WeatherApi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentDate =
                SimpleDateFormat(
                "dd/MM/yyyy",
                        Locale.getDefault())
                    .format(Date())
        lifecycleScope.launch {
            val weatherData = weatherApi.getWeatherData(49.13, 23.78)
            Log.i("WeatherAPI", "$weatherData")
        }

        setContent {
            WeatherAppTheme {
                Scaffold(contentWindowInsets = ScaffoldDefaults.contentWindowInsets) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = currentDate)
                        Text(text = appName)
                        Text(text = "$httpClient")
                    }
                }
            }
        }
    }
}