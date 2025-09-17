package com.plcoding.weatherapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.core.annotation.Single

@Single
fun provideHttpClient(): HttpClient = HttpClient(CIO)