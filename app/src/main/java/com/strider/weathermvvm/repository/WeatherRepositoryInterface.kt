package com.strider.weathermvvm.repository

import androidx.lifecycle.LiveData
import com.strider.weathermvvm.data.LocationData
import com.strider.weathermvvm.data.WeatherData
import com.strider.weathermvvm.data.WeatherForecastData
import com.strider.weathermvvm.database.entity.DBWeather
import com.strider.weathermvvm.database.entity.DBWeatherForecast

interface WeatherRepositoryInterface {

    /***
     * CurrentWeather functions
     * */
    suspend fun getWeatherByLocation(location: LocationData): LiveData<DBWeather>
    suspend fun searchWeather(query: String): LiveData<DBWeather>

    suspend fun getAllWeather(): LiveData<List<DBWeather>>
    suspend fun getWeatherByCity(name: String): LiveData<DBWeather>
    suspend fun getWeatherByCityId(id: Int): LiveData<DBWeather>

    suspend fun getWeatherByCitesList(list: List<String>): LiveData<List<DBWeather>>
    suspend fun getWeatherByCitesIdsList(list: List<Int>): LiveData<List<DBWeather>>

    suspend fun getAllWeatherIds(): LiveData<List<Int>>
    suspend fun getAllWeatherCitesNames(): LiveData<List<String>>

    suspend fun removeWeatherDataByCityId(id: Int): Int
    suspend fun removeAllOutDateWeatherData(time: Long): Int
    suspend fun removeAllWeatherData(): Int

    /***
     * ForecastWeather functions
     * */
    suspend fun getForecastWeather(cityId: Int): LiveData<List<DBWeatherForecast>>

    suspend fun getAllForecastWeather(): LiveData<List<DBWeatherForecast>>
    suspend fun getForecastByCity(name: String): LiveData<List<DBWeatherForecast>>
    suspend fun getForecastByCityId(id: Int): LiveData<List<DBWeatherForecast>>

    suspend fun getForecastByCitesList(list: List<String>): LiveData<List<DBWeatherForecast>>
    suspend fun getForecastByCitesIdsList(list: List<Int>): LiveData<List<DBWeatherForecast>>

    suspend fun getAllForecastCitesIds(): LiveData<List<Int>>
    suspend fun getAllForecastCitesNames(): LiveData<List<String>>

    suspend fun removeForecastWeatherDataByCityId(id: Int): Int
    suspend fun removeOutDateForecastWeather(time: Long): Int
    suspend fun removeAllForecastData(): Int
}
