package com.strider.weathermvvm.pagingsource

import androidx.lifecycle.LiveData
import com.strider.weathermvvm.data.WeatherData
import com.strider.weathermvvm.data.WeatherForecastData
import com.strider.weathermvvm.database.entity.DBWeather
import com.strider.weathermvvm.database.entity.DBWeatherForecast
import com.strider.weathermvvm.network.api.WeatherApiData
import com.strider.weathermvvm.network.api.WeatherForecastApi

interface LocalDataSourceInterface {

    // current weather functions
    suspend fun insertWeather(weather: WeatherApiData)
    suspend fun insertWeatherList(list: List<WeatherApiData>)

    suspend fun getAllWeather(): LiveData<List<DBWeather>>
    suspend fun getByCity(city: String): LiveData<DBWeather>
    suspend fun getByCityId(cityId: Int): LiveData<DBWeather>
    suspend fun getWeatherByCitesList(list: List<String>): LiveData<List<DBWeather>>
    suspend fun getWeatherByCitesIdsList(list: List<Int>): LiveData<List<DBWeather>>

    suspend fun getAllWeatherIds(): LiveData<List<Int>>
    suspend fun getAllWeatherCitesNames(): LiveData<List<String>>

    suspend fun deleteWeatherByCityId(id: Int): Int
    suspend fun deleteAllOutDateWeather(time: Long): Int
    suspend fun deleteAllWeather(): Int

    // forecast weather functions
    suspend fun insertForecast(weather: WeatherForecastApi)
    suspend fun insertForecastList(list: List<WeatherForecastApi>)

    suspend fun getAllForecastWeather(): LiveData<List<DBWeatherForecast>>
    suspend fun getForecastByCity(city: String): LiveData<List<DBWeatherForecast>>
    suspend fun getForecastByCityId(cityId: Int): LiveData<List<DBWeatherForecast>>
    suspend fun getForecastByCitesList(list: List<String>): LiveData<List<DBWeatherForecast>>
    suspend fun getForecastByCitesIdsList(list: List<Int>): LiveData<List<DBWeatherForecast>>

    suspend fun getAllForecastCitesIds(): LiveData<List<Int>>
    suspend fun getAllForecastCitesNames(): LiveData<List<String>>

    suspend fun deleteForecastWeatherByCityId(id: Int): Int
    suspend fun deleteOutDateForecastWeather(time: Long): Int
    suspend fun deleteAllForecastWeather(): Int
}
