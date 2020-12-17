package com.strider.weathermvvm.utils

import android.annotation.SuppressLint
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.strider.weathermvvm.data.LocationData
import splitties.init.appCtx

class LocationLiveData : MutableLiveData<LocationData>() {

    private val client = LocationServices.getFusedLocationProviderClient(appCtx)

    private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    private fun setLocationData(location: Location) {
        value = LocationData(
            longitude = location.longitude,
            latitude = location.latitude
        )
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()

        client.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    setLocationData(it)
                }
            }
        updateLocation()
    }

    @SuppressLint("MissingPermission")
    fun updateLocation() {
        client.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    override fun onInactive() {
        super.onInactive()
        client.removeLocationUpdates(locationCallback)
    }
}