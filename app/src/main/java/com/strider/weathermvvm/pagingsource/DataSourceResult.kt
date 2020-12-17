package com.strider.weathermvvm.pagingsource

sealed class DataSourceResult<out R> {

    data class Success<out T>(val data: T?): DataSourceResult<T>()
    data class Error(val t: Throwable): DataSourceResult<Nothing>()
    object Loading: DataSourceResult<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success -> "Success [data = $data]"
            is Error -> "Error [exception = $t]"
            is Loading -> "Loading"
        }
    }
}