package com.strider.weathermvvm

import android.app.Application
import com.chibatching.kotpref.Kotpref
import dagger.hilt.android.HiltAndroidApp
import splitties.init.appCtx
import timber.log.Timber

@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(appCtx)
        Timber.plant(Timber.DebugTree())
    }
}