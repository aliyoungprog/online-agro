package com.aliyoungprog.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.aliyoungprog.R
import com.aliyoungprog.data.downloadImg
import com.aliyoungprog.data.getCurrentTime
import com.aliyoungprog.data.network.WeatherService
import com.aliyoungprog.domain.entity.WeatherResponse
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentData()
    }

    companion object {
        @JvmStatic
        fun getInstance() = WeatherFragment()
        var BaseUrl = "https://api.openweathermap.org/"

        var city_name = "almaty"
    }

    private fun getCurrentData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(city_name)


        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!
                    val cur_temp = weatherResponse.main?.temp.toString()
                    var new_temp = ""
                    for (i in cur_temp.indices) {
                        if (cur_temp[i] == '.') break
                        new_temp += cur_temp[i]
                    }
                    new_temp += '°'
                    val temp_description = weatherResponse.weather?.get(0)?.description
                    val temp_city = weatherResponse.name
                    val weatherDate = getCurrentTime()
                    val weatherImg = weatherResponse.weather?.get(0)?.iconUrl
                    weather_city.text = temp_city
                    weather_date.text = weatherDate
                    weather_weather.text = temp_description
                    weather_current_weather.text = new_temp
                    context?.let {
                        downloadImg("https://openweathermap.org/img/wn/${weatherImg.plus("@2x.png")}",
                            it, weather_icon
                        )
                    }

                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Timber.d(t)
            }
        })

    }

}

