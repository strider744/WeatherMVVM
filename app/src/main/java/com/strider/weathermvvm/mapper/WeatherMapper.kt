package com.strider.weathermvvm.mapper

import com.strider.weathermvvm.data.WeatherData
import com.strider.weathermvvm.database.entity.DBWeather
import com.strider.weathermvvm.network.api.WeatherApiData

class WeatherMapper
    : WeatherMapperInterface<WeatherApiData, DBWeather, WeatherData> {
    override fun mapToEntity(response: WeatherApiData): DBWeather {
        return DBWeather(
            cityId = response.cityId,
            cityName = response.name,
            date = response.date,
            wind = response.wind,
            descriptions = response.descriptions,
            condition = response.condition
        )
    }

    override fun mapFromEntity(entity: DBWeather): WeatherData {
        return WeatherData(
            cityId = entity.cityId,
            name = entity.cityName,
            date = entity.date,
            wind = entity.wind,
            descriptions = entity.descriptions,
            condition = entity.condition
        )
    }

    override fun mapListToEntity(list: List<WeatherApiData>): List<DBWeather> {
        return list.map { mapToEntity(it) }
    }

    override fun mapListFromEntity(list: List<DBWeather>): List<WeatherData> {
        return list.map { mapFromEntity(it) }
    }
}