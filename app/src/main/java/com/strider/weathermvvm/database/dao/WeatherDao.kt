package com.strider.weathermvvm.database.dao

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
    suspend fun getAllWeather(): Flow<List<DBWeather>>

    @Query("SELECT city_id FROM weather_table")
    suspend fun getAllWeatherIds(): Flow<List<Int>>

    @Query("SELECT city_name FROM weather_table")
    suspend fun getAllCitesNames(): Flow<List<String>>

    @Query("SELECT * FROM weather_table WHERE city_name = :city")
    suspend fun getWeatherByCity(city: String): Flow<DBWeather>

    @Query("SELECT * FROM weather_table WHERE city_id = :cityId")
    suspend fun getWeatherByCityId(cityId: Int): Flow<DBWeather>

    @Query("SELECT * FROM weather_table WHERE city_name IN (:list)")
    suspend fun getWeatherByCitesList(list: List<String>): Flow<List<DBWeather>>

    @Query("SELECT * FROM weather_table WHERE city_id IN (:list)")
    suspend fun getWeatherByCitesIdsList(list: List<Int>): Flow<List<DBWeather>>

    // delete
    @Query("DELETE FROM weather_table WHERE city_id = :cityId")
    suspend fun deleteWeatherByCityId(cityId: Int): Int

    @Query("DELETE FROM weather_table WHERE time < :time")
    suspend fun deleteAllOutDateWeather(time: Long): Int

    @Query("DELETE FROM weather_table")
    suspend fun deleteAllWeather(): Int
}