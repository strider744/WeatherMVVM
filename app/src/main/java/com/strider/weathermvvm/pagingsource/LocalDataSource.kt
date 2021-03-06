package com.strider.weathermvvm.pagingsource

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.strider.weathermvvm.data.WeatherData
import com.strider.weathermvvm.data.WeatherForecastData
import com.strider.weathermvvm.database.WeatherDatabase
import com.strider.weathermvvm.database.entity.DBWeather
import com.strider.weathermvvm.database.entity.DBWeatherForecast
import com.strider.weathermvvm.mapper.ForecastMapper
import com.strider.weathermvvm.mapper.WeatherMapper
import com.strider.weathermvvm.network.api.WeatherApiData
import com.strider.weathermvvm.network.api.WeatherForecastApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource
@Inject constructor(
    private val database: WeatherDatabase,
    private val weatherMapper: WeatherMapper,
    private val forecastMapper: ForecastMapper
) : LocalDataSourceInterface {

    override suspend fun insertWeather(weather: WeatherApiData) {
        database.weatherDao.insertWeather(weatherMapper.mapToEntity(weather))
    }

    override suspend fun insertWeatherList(list: List<WeatherApiData>) {
        database.weatherDao.insertWeatherList(weatherMapper.mapListToEntity(list))
    }

    override suspend fun getAllWeather(): LiveData<List<DBWeather>> {
        return database.weatherDao.getAllWeather()
    }

    override suspend fun getAllWeatherIds(): LiveData<List<Int>> {
        return database.weatherDao.getAllWeatherIds()
    }

    override suspend fun getAllWeatherCitesNames(): LiveData<List<String>> {
        return database.weatherDao.getAllCitesNames()
    }

    override suspend fun getByCity(city: String): LiveData<DBWeather> {
        return database.weatherDao.getWeatherByCity(city)
    }

    override suspend fun getByCityId(cityId: Int): LiveData<DBWeather> {
        return database.weatherDao.getWeatherByCityId(cityId)
    }

    override suspend fun getWeatherByCitesList(list: List<String>): LiveData<List<DBWeather>> {
        return database.weatherDao.getWeatherByCitesList(list)
    }

    override suspend fun getWeatherByCitesIdsList(list: List<Int>): LiveData<List<DBWeather>> {
        return database.weatherDao.getWeatherByCitesIdsList(list)
    }

    override suspend fun deleteWeatherByCityId(id: Int): Int {
        return database.weatherDao.deleteWeatherByCityId(id)
    }

    override suspend fun deleteAllOutDateWeather(time: Long): Int {
        return database.weatherDao.deleteAllOutDateWeather(time)
    }

    override suspend fun deleteAllWeather(): Int {
        return database.weatherDao.deleteAllWeather()
    }

    override suspend fun insertForecast(weather: WeatherForecastApi) {
        database.forecastWeatherDao.insertForecastList(forecastMapper.mapResponseToEntity(weather))
    }

    override suspend fun insertForecastList(list: List<WeatherForecastApi>) {
        list.forEach {
            database.forecastWeatherDao.insertForecastList(forecastMapper.mapResponseToEntity(it))
        }
    }

    override suspend fun getAllForecastWeather(): LiveData<List<DBWeatherForecast>> {
        return database.forecastWeatherDao.getAllForecastWeather()
    }

    override suspend fun getAllForecastCitesIds(): LiveData<List<Int>> {
        return database.forecastWeatherDao.getAllForecastCitesIds()
    }

    override suspend fun getAllForecastCitesNames(): LiveData<List<String>> {
        return database.forecastWeatherDao.getAllForecastCitesNames()
    }

    override suspend fun getForecastByCity(city: String): LiveData<List<DBWeatherForecast>> {
        return database.forecastWeatherDao.getForecastByCity(city)
    }

    override suspend fun getForecastByCityId(cityId: Int): LiveData<List<DBWeatherForecast>> {
        return database.forecastWeatherDao.getForecastByCityId(cityId)
    }

    override suspend fun getForecastByCitesList(list: List<String>): LiveData<List<DBWeatherForecast>> {
        return database.forecastWeatherDao.getForecastByCitesList(list)
    }

    override suspend fun getForecastByCitesIdsList(list: List<Int>): LiveData<List<DBWeatherForecast>> {
        return database.forecastWeatherDao.getForecastByCityIds(list)
    }

    override suspend fun deleteForecastWeatherByCityId(id: Int): Int {
        return database.forecastWeatherDao.deleteForecastWeatherByCityId(id)
    }

    override suspend fun deleteOutDateForecastWeather(time: Long): Int {
        return database.forecastWeatherDao.deleteOutDateForecastWeather(time)
    }

    override suspend fun deleteAllForecastWeather(): Int {
        return database.forecastWeatherDao.deleteAllForecastWeather()
    }
}