package com.strider.weathermvvm.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherData(

    val cityId: Int,
    val name: String,
    val date: Long,
    val wind: WindData,
    val descriptions: List<WeatherDescription>,
    val condition: WeatherConditionData
) : Parcelable