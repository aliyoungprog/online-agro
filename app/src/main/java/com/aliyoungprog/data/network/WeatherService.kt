package com.aliyoungprog.data.network

import com.aliyoungprog.domain.entity.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    private val API_KEY: String
        get() = "2e42063b9f4ec950d7b8db413aefa85c"

    @GET("data/2.5/weather?units=metric&lang=ru")
    fun getCurrentWeatherData(@Query("q") city_name: String, @Query("APPID") app_id: String = API_KEY): Call<WeatherResponse>
}
