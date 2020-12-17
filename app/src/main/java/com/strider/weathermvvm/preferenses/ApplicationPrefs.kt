package com.strider.weathermvvm.preferenses

import com.chibatching.kotpref.KotprefModel

object ApplicationPrefs : KotprefModel() {
    var apiKey by stringPref("cac5505ab5c3ddd005ad46d7bbc95f05")
    var appId by stringPref("com.strider.weather")
}