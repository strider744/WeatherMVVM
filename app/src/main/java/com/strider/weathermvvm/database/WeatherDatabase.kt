package com.strider.weathermvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.strider.weathermvvm.database.converter.WeatherDescriptionConverter
import com.strider.weathermvvm.database.dao.ForecastWeatherDao
import com.strider.weathermvvm.database.dao.WeatherDao
import com.strider.weathermvvm.database.entity.DBWeather
import com.strider.weathermvvm.database.entity.DBWeatherForecast

@Database(
    entities = [DBWeather::class, DBWeatherForecast::class],
    version = 3,
    exportSchema = true
)
@TypeConverters(WeatherDescriptionConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
    abstract val forecastWeatherDao: ForecastWeatherDao
}