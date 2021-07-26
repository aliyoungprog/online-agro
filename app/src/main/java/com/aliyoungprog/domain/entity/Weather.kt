package com.aliyoungprog.domain.entity

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    @SerializedName("sys")
    var sys: Sys? = null,

    @SerializedName("main")
    var main: Main? = null,

    @SerializedName("weather")
    var weather: List<Weather>? = null,

    @SerializedName("name")
    var name: String? = null
)

data class Weather(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("main")
    var main: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("icon")
    var iconUrl: String? = null
)

data class Main (
    @SerializedName("temp")
    var temp: Float = 0.0f,
    @SerializedName("humidity")
    var humidity: Float = 0.0f,
    @SerializedName("pressure")
    var pressure: Float = 0.0f,
    @SerializedName("temp_min")
    var temp_min: Float = 0.0f,
    @SerializedName("temp_max")
    var temp_max: Float = 0.0f
)

data class Sys(
    @SerializedName("country")
    var country: String? = null,
)

