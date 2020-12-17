package com.strider.weathermvvm.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.strider.weathermvvm.data.CityData
import com.strider.weathermvvm.data.WeatherConditionData
import com.strider.weathermvvm.data.WeatherDescription
import com.strider.weathermvvm.data.WindData

@Entity(tableName = "weather_forecast")
data class DBWeatherForecast(

    @ColumnInfo(name = "unique_id")
    @PrimaryKey(autoGenerate = true)
    val uId: Int = 0,

    @ColumnInfo(name = "forecast_date")
    val date: Long,

    @Embedded
    val city: CityData,

    @Embedded
    val wind: WindData,

    @ColumnInfo(name = "description")
    val descriptions: List<WeatherDescription>,

    @Embedded
    val condition: WeatherConditionData
)