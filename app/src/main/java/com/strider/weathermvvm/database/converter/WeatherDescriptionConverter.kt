package com.strider.weathermvvm.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.strider.weathermvvm.data.WeatherDescription
import java.lang.reflect.Type

class WeatherDescriptionConverter {
    private val gson = Gson()

    private val type: Type = object : TypeToken<List<WeatherDescription?>?>() {}.type

    @TypeConverter
    fun descriptionToString(list: List<WeatherDescription?>?): String {
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun descriptionToList(json: String?): List<WeatherDescription> {
        return gson.fromJson(json, type)
    }
}