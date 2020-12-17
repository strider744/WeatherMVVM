package com.strider.weathermvvm.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeNotNull(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) =
    observe(lifecycleOwner, { it?.let { observer(it) } })