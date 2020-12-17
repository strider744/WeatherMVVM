package com.strider.weathermvvm

interface Action {
    class Error(val error: Throwable) : Action
}