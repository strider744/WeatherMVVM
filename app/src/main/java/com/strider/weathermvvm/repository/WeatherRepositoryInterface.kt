package com.strider.weathermvvm.repository

import androidx.lifecycle.LiveData
import com.strider.weathermvvm.data.LocationData
import com.strider.weathermvvm.data.WeatherData
import com.strider.weathermvvm.data.WeatherForecastData

interface WeatherRepositoryInterface {

    /***
     * CurrentWeather functions
     * */
    suspend fun getWeatherByLocation(location: LocationData): LiveData<WeatherData>
    suspend fun searchWeather(query: String): LiveData<WeatherData>

    suspend fun getAllWeather(): LiveData<List<WeatherData>>
    suspend fun getWeatherByCity(name: String): LiveData<WeatherData>
    suspend fun getWeatherByCityId(id: Int): LiveData<WeatherData>

    suspend fun getWeatherByCitesList(list: List<String>): LiveData<List<WeatherData>>
    suspend fun getWeatherByCitesIdsList(list: List<Int>): LiveData<List<WeatherData>>

    suspend fun getAllWeatherIds(): LiveData<List<Int>>
    suspend fun getAllWeatherCitesNames(): LiveData<List<String>>

    suspend fun removeWeatherDataByCityId(id: Int): Int
    suspend fun removeAllOutDateWeatherData(time: Long): Int
    suspend fun removeAllWeatherData(): Int

    /***
     * ForecastWeather functions
     * */
    suspend fun getForecastWeather(cityId: Int): LiveData<List<WeatherForecastData>>

    suspend fun getAllForecastWeather(): LiveData<List<WeatherForecastData>>
    suspend fun getForecastByCity(name: String): LiveData<List<WeatherForecastData>>
    suspend fun getForecastByCityId(id: Int): LiveData<List<WeatherForecastData>>

    suspend fun getForecastByCitesList(list: List<String>): LiveData<List<WeatherForecastData>>
    suspend fun getForecastByCitesIdsList(list: List<Int>): LiveData<List<WeatherForecastData>>

    suspend fun getAllForecastCitesIds(): LiveData<List<Int>>
    suspend fun getAllForecastCitesNames(): LiveData<List<String>>

    suspend fun removeForecastWeatherDataByCityId(id: Int): Int
    suspend fun removeOutDateForecastWeather(time: Long): Int
    suspend fun removeAllForecastData(): Int
}
