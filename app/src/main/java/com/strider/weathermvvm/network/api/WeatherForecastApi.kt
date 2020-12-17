package com.strider.weathermvvm.network.api

import com.google.gson.annotations.SerializedName
import com.strider.weathermvvm.data.CityData
import com.strider.weathermvvm.data.WeatherForecastApiData

data class WeatherForecastApi(

    @SerializedName("list")
    val weather: List<WeatherForecastApiData>,

    val city: CityData
)