package com.plcoding.weatherapp.data

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.plcoding.weatherapp.domain.location.LocationTracker
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class DefaultLocationTracker(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val app: Application
): LocationTracker {
    override suspend fun getCurrentLocation(): Location? {
        val hasFinePermission = ContextCompat.checkSelfPermission(
            app,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasCoarsePermission = ContextCompat.checkSelfPermission(
            app,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val isGpsEnabled = with(app.getSystemService(Context.LOCATION_SERVICE) as LocationManager){
            isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
            isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

        if(!hasFinePermission || !hasCoarsePermission || !isGpsEnabled) return null

        return try {
            fusedLocationProviderClient.lastLocation.await()
        } catch (e: Exception){
            Log.e("LocationTracker", "getCurrentLocation: ", e)
            null
        }
    }
}