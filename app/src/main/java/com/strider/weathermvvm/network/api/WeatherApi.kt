package com.strider.weathermvvm.network.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val METRIC = "metric"
        const val IMPERIAL = "imperial"
        const val BASE_URL = "http://api.openweathermap.org"
    }

    @GET("data/2.5/weather")
    fun getByCity(
        @Query("q") query: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Single<WeatherApiData>

    @GET("data/2.5/weather")
    fun getByCoordinate(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Single<WeatherApiData>

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("id") cityId: Int,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Single<WeatherForecastApi>
}