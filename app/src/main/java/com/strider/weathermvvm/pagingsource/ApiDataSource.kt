package com.strider.weathermvvm.pagingsource

import com.strider.weathermvvm.network.api.WeatherApi
import com.strider.weathermvvm.network.api.WeatherApi.Companion.METRIC
import com.strider.weathermvvm.data.LocationData
import com.strider.weathermvvm.network.api.WeatherApiData
import com.strider.weathermvvm.network.api.WeatherForecastApi
import com.strider.weathermvvm.preferenses.ApplicationPrefs.apiKey
import com.strider.weathermvvm.utils.runOnIOThread
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiDataSource
@Inject constructor(
    private val api: WeatherApi
) : ApiDataSourceInterface {
    override suspend fun getWeather(location: LocationData)
            : Single<WeatherApiData> {
        return runOnIOThread {
            api.getByCoordinate(
                lon = location.longitude,
                lat = location.latitude,
                apiKey = apiKey,
                units = METRIC
            )
        }
    }

    override suspend fun getWeatherForecast(cityId: Int)
            : Single<WeatherForecastApi> {
        return runOnIOThread {
            api.getForecast(cityId = cityId, apiKey = apiKey, units = METRIC)
        }
    }

    override suspend fun searchWeather(query: String)
            : Single<WeatherApiData> {
        return runOnIOThread {
            api.getByCity(query = query, apiKey = apiKey, units = METRIC)

        }
    }
}