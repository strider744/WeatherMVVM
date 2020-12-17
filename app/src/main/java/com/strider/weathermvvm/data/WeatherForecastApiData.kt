package com.strider.weathermvvm.data

import com.google.gson.annotations.SerializedName

data class WeatherForecastApiData(

    val id: Int,

    @SerializedName("dt_txt")
    val date: Long,

    val wind: WindData,

    @SerializedName("weather")
    val descriptions: List<WeatherDescription>,

    @SerializedName("main")
    val condition: WeatherConditionData
)
