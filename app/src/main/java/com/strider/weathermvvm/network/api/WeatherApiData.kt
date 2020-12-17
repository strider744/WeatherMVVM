package com.strider.weathermvvm.network.api

import com.google.gson.annotations.SerializedName
import com.strider.weathermvvm.data.WeatherConditionData
import com.strider.weathermvvm.data.WeatherDescription
import com.strider.weathermvvm.data.WindData

data class WeatherApiData(
    @SerializedName("id")
    val cityId: Int,

    val name: String,

    val date: Long,

    val wind: WindData,

    @SerializedName("weather")
    val descriptions: List<WeatherDescription>,

    @SerializedName("main")
    val condition: WeatherConditionData
)