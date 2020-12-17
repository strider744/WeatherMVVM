package com.strider.weathermvvm.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.strider.weathermvvm.Action
import com.strider.weathermvvm.data.LocationData
import com.strider.weathermvvm.data.WeatherData
import com.strider.weathermvvm.data.WeatherForecastData
import com.strider.weathermvvm.database.entity.DBWeather
import com.strider.weathermvvm.database.entity.DBWeatherForecast
import com.strider.weathermvvm.mapper.ForecastMapper
import com.strider.weathermvvm.mapper.WeatherMapper
import com.strider.weathermvvm.repository.WeatherRepository
import com.strider.weathermvvm.utils.*
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel @ViewModelInject constructor(
    private val repository: WeatherRepository,
    private val weatherMapper: WeatherMapper,
    private val forecastMapper: ForecastMapper
) : ViewModel() {

    private val currentLocation = LocationLiveData()
    private val currentCitesList = MutableLiveData<LinkedList<String>>()
    private val currentCitesIdsList = MutableLiveData<LinkedList<Int>>()
    private val allDBWeatherList = liveData<List<DBWeather>> {
            repository.getAllWeather()
        }

    val allWeatherList : LiveData<List<WeatherData>> = Transformations.map(allDBWeatherList) {
        weatherMapper.mapListFromEntity(it)
    }

    private val forecastWeatherList = liveData<List<DBWeatherForecast>> {
                repository.getAllForecastWeather()
            }

    private val currentDBWeather = Transformations.switchMap(currentLocation) {
        liveData<DBWeather> {
            currentLocation.value?.let {
                repository.getWeatherByLocation(it)
            }
        }
    }

    val currentWeather: LiveData<WeatherData>  = Transformations.map(currentDBWeather) {
        weatherMapper.mapFromEntity(it)
    }

    fun setLocation(location: LocationData) {
        currentLocation.value = location
    }

    fun updateLocation() {
        currentLocation.updateLocation()
    }

    fun getWeatherByCity(name: String) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.searchWeather(name)
        }
    }
}