package com.strider.weathermvvm.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.strider.weathermvvm.data.WeatherConditionData
import com.strider.weathermvvm.data.WeatherDescription
import com.strider.weathermvvm.data.WindData

@Entity(tableName = "weather_table")
data class DBWeather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "city_id")
    val cityId: Int,

    @ColumnInfo(name = "city_name")
    val cityName: String,

    @ColumnInfo(name = "time")
    val date: Long,

    @Embedded
    val wind: WindData,

    @ColumnInfo(name = "weather_details")
    val descriptions: List<WeatherDescription>,

    @Embedded
    val condition: WeatherConditionData
)