package com.strider.weathermvvm.data

import com.google.gson.annotations.SerializedName

data class WeatherForecastData(

    val id: Int,

    @SerializedName("dt_txt")
    val date: Long,

    val wind: WindData,

    val city: CityData,

    @SerializedName("weather")
    val descriptions: List<WeatherDescription>,

    @SerializedName("main")
    val condition: WeatherConditionData
)
