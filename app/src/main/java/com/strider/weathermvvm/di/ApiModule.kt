package com.strider.weathermvvm.di

import com.strider.weathermvvm.network.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {

    // okhttp
    private val okHttpClient by lazy { OkHttpClient.Builder().addInterceptor(logging).build() }

    private val logging by lazy {
        HttpLoggingInterceptor {
            Timber.tag("OkHttp").d(it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(WeatherApi.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)
}