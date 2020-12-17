package com.strider.weathermvvm.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityData(
    val cityId: Int,
    val name: String,
    val country: String
): Parcelable