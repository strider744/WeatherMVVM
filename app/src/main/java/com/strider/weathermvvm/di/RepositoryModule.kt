package com.strider.weathermvvm.di

import com.strider.weathermvvm.pagingsource.ApiDataSource
import com.strider.weathermvvm.pagingsource.LocalDataSource
import com.strider.weathermvvm.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        apiDataSource: ApiDataSource,
        localDataSource: LocalDataSource
    ): WeatherRepository = WeatherRepository(apiDataSource, localDataSource)
}