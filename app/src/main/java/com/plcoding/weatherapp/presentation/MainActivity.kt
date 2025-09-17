package com.plcoding.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    val appName by inject<String>(named("app_name"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentDate =
                SimpleDateFormat(
                "dd/MM/yyyy",
                        Locale.getDefault())
                    .format(Date())

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
                    }
                }
            }
        }
    }
}