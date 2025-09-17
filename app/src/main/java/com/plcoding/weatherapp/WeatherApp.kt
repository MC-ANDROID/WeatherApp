package com.plcoding.weatherapp

import android.app.Application
import com.plcoding.weatherapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            modules(AppModule().module)
        }
    }
}