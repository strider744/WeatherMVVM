package com.strider.weathermvvm.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WindData(
    val speed: Double,
    val deg: Int
) : Parcelable