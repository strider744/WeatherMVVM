package com.strider.weathermvvm.pagingsource

import com.strider.weathermvvm.data.LocationData
import com.strider.weathermvvm.network.api.WeatherApiData
import com.strider.weathermvvm.network.api.WeatherForecastApi
import io.reactivex.rxjava3.core.Single

interface ApiDataSourceInterface {

    suspend fun getWeather(location: LocationData): Single<WeatherApiData>
    suspend fun getWeatherForecast(cityId: Int): Single<WeatherForecastApi>
    suspend fun searchWeather(query: String): Single<WeatherApiData>
}
