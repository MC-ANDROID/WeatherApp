package com.plcoding.weatherapp.di

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.annotation.Named


@Module
class AppModule {
    @Single
    @Named("app_name")
    fun provideAppName(): String {
        return "Weather App"
    }
}