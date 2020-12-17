package com.strider.weathermvvm.utils

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.rx3.await
import timber.log.Timber

// переход на IO поток
suspend fun <T> runOnIOThread(
    block: suspend CoroutineScope.() -> T
): T = withContext(Dispatchers.IO, block)

// переход на IO поток с ловлей исключений
suspend fun <T> runCatchingOnIOThread(
    block: suspend CoroutineScope.() -> T
): T = runCatching { withContext(Dispatchers.IO, block) }
    .getOrElse {
        Timber.e("Error ${it.localizedMessage}")
        withContext(Dispatchers.IO, block)
    }

// переход на main поток
suspend fun <T> runOnMainThread(block: suspend CoroutineScope.() -> T): T =
    runCatching { withContext(Dispatchers.Main, block) }
        .getOrElse {
            Timber.e("Error ${it.localizedMessage}")
            withContext(Dispatchers.Main, block = block)
        }

// переход на main поток с ловлей исключений
suspend fun <T> runCatchingOnMainThread(block: suspend CoroutineScope.() -> T): T =
    runCatching { withContext(Dispatchers.Main, block) }
        .getOrElse {
            Timber.e("Error ${it.localizedMessage}")
            withContext(Dispatchers.Main, block)
        }

// старт main потока
fun startMainThread(block: suspend CoroutineScope.() -> Unit): Job =
    runCatching { GlobalScope.launch(Dispatchers.Main, block = block) }
        .getOrElse {
            Timber.e("Error ${it.localizedMessage}")
            GlobalScope.launch(Dispatchers.Main, block = block)
        }

// старт IO потока
fun startIOThread(block: suspend CoroutineScope.() -> Unit): Job =
    runCatching { GlobalScope.launch(Dispatchers.IO, block = block) }
        .getOrElse {
            Timber.e("Error ${it.localizedMessage}")
            GlobalScope.launch(Dispatchers.IO, block = block)
        }

// старт IO потока
fun startIOThreadCatching(block: suspend CoroutineScope.() -> Unit): Job =
    runCatching { GlobalScope.launch(Dispatchers.IO, block = block) }
        .getOrElse {
            Timber.e("Error ${it.localizedMessage}")
            GlobalScope.launch(Dispatchers.IO, block = block)
        }

// старт Default потока
fun <T> startBackgroundThread(block: suspend CoroutineScope.() -> Unit): Job =
    runCatching { GlobalScope.launch(Dispatchers.Default, block = block) }
        .getOrElse {
            Timber.e("Error ${it.localizedMessage}")
            GlobalScope.launch(Dispatchers.Default, block = block)
        }