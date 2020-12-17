package com.strider.weathermvvm.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.strider.weathermvvm.database.entity.DBWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    /***
     * CurrentWeather functions
     * */

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: DBWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherList(list: List<DBWeather>)

    // query
    @Query("SELECT * FROM weather_table")
    fun getAllWeather(): LiveData<List<DBWeather>>

    @Query("SELECT city_id FROM weather_table")
    fun getAllWeatherIds(): LiveData<List<Int>>

    @Query("SELECT city_name FROM weather_table")
    fun getAllCitesNames(): LiveData<List<String>>

    @Query("SELECT * FROM weather_table WHERE city_name = :city")
    fun getWeatherByCity(city: String): LiveData<DBWeather>

    @Query("SELECT * FROM weather_table WHERE city_id = :cityId")
     fun getWeatherByCityId(cityId: Int): LiveData<DBWeather>

    @Query("SELECT * FROM weather_table WHERE city_name IN (:list)")
    fun getWeatherByCitesList(list: List<String>): LiveData<List<DBWeather>>

    @Query("SELECT * FROM weather_table WHERE city_id IN (:list)")
    fun getWeatherByCitesIdsList(list: List<Int>): LiveData<List<DBWeather>>

    // delete
    @Query("DELETE FROM weather_table WHERE city_id = :cityId")
    suspend fun deleteWeatherByCityId(cityId: Int): Int

    @Query("DELETE FROM weather_table WHERE time < :time")
    suspend fun deleteAllOutDateWeather(time: Long): Int

    @Query("DELETE FROM weather_table")
    suspend fun deleteAllWeather(): Int
}