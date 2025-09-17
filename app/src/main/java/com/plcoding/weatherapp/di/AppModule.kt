package com.plcoding.weatherapp.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.annotation.Named


@Module
@ComponentScan(
    "com.plcoding.weatherapp.di",
    "com.plcoding.weatherapp.data.remote")
class AppModule {
    @Single
    @Named("app_name")
    fun provideAppName(): String {
        return "Weather App"
    }
}