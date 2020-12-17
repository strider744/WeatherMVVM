package com.strider.weathermvvm.utils

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.rx3.await

// подписываемся в background потоке
fun <T> Single<T>.async(): Single<T> = this
    .subscribeOn(Schedulers.io())
    .retry(4)

// завешает сопрограмму и возвращает результат
suspend fun <T> Single<T>.asyncAwait(): T = this
    .async()
    .await()