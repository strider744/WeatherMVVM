package com.strider.weathermvvm.di

import com.strider.weathermvvm.database.WeatherDatabase
import com.strider.weathermvvm.mapper.ForecastMapper
import com.strider.weathermvvm.mapper.WeatherMapper
import com.strider.weathermvvm.network.api.WeatherApi
import com.strider.weathermvvm.pagingsource.ApiDataSource
import com.strider.weathermvvm.pagingsource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideApiDataSource(api: WeatherApi): ApiDataSource =
        ApiDataSource(api)

    @Provides
    @Singleton
    fun provideWeatherMapper(): WeatherMapper =
        WeatherMapper()

    @Provides
    @Singleton
    fun provideForecastMapper(): ForecastMapper =
        ForecastMapper()

    @Provides
    @Singleton
    fun provideLocalDataSource(database: WeatherDatabase): LocalDataSource =
        LocalDataSource(database, provideWeatherMapper(), provideForecastMapper())
}