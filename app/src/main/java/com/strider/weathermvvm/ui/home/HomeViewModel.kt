package com.strider.weathermvvm.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.strider.weathermvvm.Action
import com.strider.weathermvvm.data.LocationData
import com.strider.weathermvvm.data.WeatherData
import com.strider.weathermvvm.data.WeatherForecastData
import com.strider.weathermvvm.repository.WeatherRepository
import com.strider.weathermvvm.utils.*
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel @ViewModelInject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    val dispatcher: PublishSubject<Action> = PublishSubject.create()

    private val currentLocation = LocationLiveData()
    private val currentCitesList = MutableLiveData<LinkedList<String>>()
    private val currentCitesIdsList = MutableLiveData<LinkedList<Int>>()
    val allWeatherList = liveData<List<WeatherData>> {
            repository.getAllWeather().asLiveData(Dispatchers.IO)
        }

    private val forecastWeatherList =
        liveData<List<WeatherForecastData>> {
                repository.getAllForecastWeather().asLiveData(Dispatchers.IO)
            }

    val currentWeather = Transformations.switchMap(currentLocation) {
        liveData<WeatherData> {
            currentLocation.value?.let {
                repository.getWeatherByLocation(it).asLiveData(Dispatchers.IO)
            }
        }
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