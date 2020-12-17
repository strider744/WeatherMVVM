package com.strider.weathermvvm.mapper

import com.strider.weathermvvm.data.CityData
import com.strider.weathermvvm.data.WeatherForecastApiData
import com.strider.weathermvvm.data.WeatherForecastData
import com.strider.weathermvvm.database.entity.DBWeatherForecast
import com.strider.weathermvvm.network.api.WeatherForecastApi
import javax.inject.Inject

class ForecastMapper
@Inject
constructor() :
    ForecastMapperInterface<WeatherForecastApi, WeatherForecastApiData, DBWeatherForecast, WeatherForecastData, CityData> {

    override fun mapToEntity(data: WeatherForecastApiData, subData: CityData): DBWeatherForecast {
        return DBWeatherForecast(
            uId = data.id,
            date = data.date,
            city = subData,
            wind = data.wind,
            descriptions = data.descriptions,
            condition = data.condition,
        )
    }

    override fun mapResponseToEntity(response: WeatherForecastApi): List<DBWeatherForecast> {
        return response.weather.map {
            mapToEntity(it, response.city)
        }
    }

    override fun mapFromEntity(entity: DBWeatherForecast): WeatherForecastData {
        return WeatherForecastData(
            id = entity.uId,
            date = entity.date,
            city = entity.city,
            wind = entity.wind,
            descriptions = entity.descriptions,
            condition = entity.condition,
        )
    }

    override fun mapListFromEntity(list: List<DBWeatherForecast>): List<WeatherForecastData> {
        return list.map { mapFromEntity(it) }
    }
}