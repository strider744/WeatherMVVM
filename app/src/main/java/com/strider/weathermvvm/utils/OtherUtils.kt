package com.strider.weathermvvm.utils

import timber.log.Timber

fun log(clazz: Class<Any>, message: String) =
    Timber.tag("WARNING ${clazz.name}")
        .d(message)

fun log(clazz: Class<Any>, it: Throwable) =
    Timber.tag("ERROR ${clazz.name}")
        .e(it.localizedMessage)

