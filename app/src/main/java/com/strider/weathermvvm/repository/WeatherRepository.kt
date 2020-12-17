package com.strider.weathermvvm.repository

import androidx.lifecycle.LiveData
import com.strider.weathermvvm.data.LocationData
import com.strider.weathermvvm.data.WeatherData
import com.strider.weathermvvm.data.WeatherForecastData
import com.strider.weathermvvm.database.entity.DBWeather
import com.strider.weathermvvm.database.entity.DBWeatherForecast
import com.strider.weathermvvm.pagingsource.ApiDataSource
import com.strider.weathermvvm.pagingsource.LocalDataSource
import com.strider.weathermvvm.utils.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.rx3.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository
@Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val localDataSource: LocalDataSource
) : WeatherRepositoryInterface {

    override suspend fun getWeatherByLocation(
        location: LocationData
    ): LiveData<DBWeather> {
        return apiDataSource.getWeather(location)
            .doOnSuccess { response ->
                startIOThread {
                    localDataSource.insertWeather(response)
                }
            }
            .doOnError {
                log(this@WeatherRepository.javaClass, it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .async().let {
                localDataSource.getByCityId(it.await().cityId)
            }
    }

    override suspend fun searchWeather(query: String): LiveData<DBWeather> {
        return runOnIOThread {
            apiDataSource.searchWeather(query)
                .doOnSuccess { response ->
                    startIOThread {
                        log(this@WeatherRepository.javaClass, "searchWeather success")
                        localDataSource.insertWeather(response)
                    }
                }
                .doOnError {
                    log(this@WeatherRepository.javaClass, it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .async().let {
                    localDataSource.getByCityId(it.await().cityId)
                }
        }
    }

    override suspend fun getAllWeather(): LiveData<List<DBWeather>> =
        runOnIOThread { localDataSource.getAllWeather() }

    override suspend fun getWeatherByCity(name: String): LiveData<DBWeather> =
        runOnIOThread { localDataSource.getByCity(name) }

    override suspend fun getWeatherByCityId(id: Int): LiveData<DBWeather> =
        runOnIOThread { localDataSource.getByCityId(id) }

    override suspend fun getWeatherByCitesList(list: List<String>): LiveData<List<DBWeather>> =
        localDataSource.getWeatherByCitesList(list)

    override suspend fun getWeatherByCitesIdsList(list: List<Int>): LiveData<List<DBWeather>> =
        localDataSource.getWeatherByCitesIdsList(list)

    override suspend fun getAllWeatherIds(): LiveData<List<Int>> =
        localDataSource.getAllWeatherIds()

    override suspend fun getAllWeatherCitesNames(): LiveData<List<String>> {
        return localDataSource.getAllWeatherCitesNames()
    }

    override suspend fun removeAllWeatherData(): Int {
        return runOnIOThread { localDataSource.deleteAllWeather() }
    }

    override suspend fun removeAllOutDateWeatherData(time: Long): Int {
        return runOnIOThread { localDataSource.deleteAllOutDateWeather(time) }
    }

    override suspend fun removeWeatherDataByCityId(id: Int): Int {
        return runOnIOThread { localDataSource.deleteWeatherByCityId(id) }
    }

    override suspend fun getForecastWeather(
        cityId: Int
    ): LiveData<List<DBWeatherForecast>> {
        return apiDataSource.getWeatherForecast(cityId)
            .doOnSuccess { response ->
                startIOThread {
                    localDataSource.insertForecast(response)
                }
            }
            .doOnError {
                log(this@WeatherRepository.javaClass, it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .async().let {
                localDataSource.getForecastByCityId(cityId)
            }
    }

    override suspend fun getAllForecastWeather(): LiveData<List<DBWeatherForecast>> {
        return localDataSource.getAllForecastWeather()
    }

    override suspend fun getForecastByCity(name: String): LiveData<List<DBWeatherForecast>> {
        return localDataSource.getForecastByCity(name)
    }

    override suspend fun getForecastByCityId(id: Int): LiveData<List<DBWeatherForecast>> {
        return localDataSource.getForecastByCityId(id)
    }

    override suspend fun getForecastByCitesList(list: List<String>): LiveData<List<DBWeatherForecast>> {
        return localDataSource.getForecastByCitesList(list)
    }

    override suspend fun getForecastByCitesIdsList(list: List<Int>): LiveData<List<DBWeatherForecast>> {
        return localDataSource.getForecastByCitesIdsList(list)
    }

    override suspend fun getAllForecastCitesIds(): LiveData<List<Int>> {
        return localDataSource.getAllForecastCitesIds()
    }

    override suspend fun getAllForecastCitesNames(): LiveData<List<String>> {
        return localDataSource.getAllForecastCitesNames()
    }

    override suspend fun removeForecastWeatherDataByCityId(id: Int): Int {
        return localDataSource.deleteForecastWeatherByCityId(id)
    }

    override suspend fun removeOutDateForecastWeather(time: Long): Int {
        return localDataSource.deleteAllOutDateWeather(time)
    }

    override suspend fun removeAllForecastData(): Int {
        return localDataSource.deleteAllForecastWeather()
    }
}