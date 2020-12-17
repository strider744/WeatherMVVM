package com.strider.weathermvvm.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.strider.weathermvvm.database.entity.DBWeatherForecast
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastWeatherDao {
    /***
     * ForecastWeather functions
     * */

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(weather: DBWeatherForecast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecastList(list: List<DBWeatherForecast>)

    // query
    @Query("SELECT * FROM weather_forecast")
    fun getAllForecastWeather(): LiveData<List<DBWeatherForecast>>

    @Query("SELECT cityId FROM weather_forecast")
    fun getAllForecastCitesIds(): LiveData<List<Int>>

    @Query("SELECT name FROM weather_forecast")
    fun getAllForecastCitesNames(): LiveData<List<String>>

    @Query("SELECT * FROM weather_forecast WHERE name = :city")
    fun getForecastByCity(city: String): LiveData<List<DBWeatherForecast>>

    @Query("SELECT * FROM weather_forecast WHERE name IN (:list)")
    fun getForecastByCitesList(list: List<String>): LiveData<List<DBWeatherForecast>>

    @Query("SELECT * FROM weather_forecast WHERE cityId IN (:list)")
    fun getForecastByCityIds(list: List<Int>): LiveData<List<DBWeatherForecast>>

    @Query("SELECT * FROM weather_forecast WHERE cityId = :cityId")
    fun getForecastByCityId(cityId: Int): LiveData<List<DBWeatherForecast>>

    // delete
    @Query("DELETE FROM weather_forecast WHERE cityId = :cityId")
    suspend fun deleteForecastWeatherByCityId(cityId: Int): Int

    @Query("DELETE FROM weather_forecast WHERE forecast_date < :time")
    suspend fun deleteOutDateForecastWeather(time: Long): Int

    @Query("DELETE FROM weather_forecast")
    suspend fun deleteAllForecastWeather(): Int
}