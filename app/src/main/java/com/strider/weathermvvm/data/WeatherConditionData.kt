package com.strider.weathermvvm.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherConditionData(
    val temp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int
) : Parcelable
