package com.plcoding.weatherapp.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.annotation.Named


@Module
@ComponentScan(
    "com.plcoding.weatherapp.di",
    "com.plcoding.weatherapp.data",
    "com.plcoding.weatherapp.presentation")
class AppModule {
    @Single
    @Named("app_name")
    fun provideAppName(): String {
        return "Weather App"
    }

    @Single
    fun provideFusedLocationProviderClient(app: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}