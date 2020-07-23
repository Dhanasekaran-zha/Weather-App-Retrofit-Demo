package com.example.weatherappretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weatherappretrofitdemo.UserResponse.WeatherResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastBtn.setOnClickListener {
            val map=HashMap<String,String>(2)
            val cityName=city.text.toString()
            map.put("q",cityName)
            map.put("appid","439d4b804bc8187953eb36d2a8c26a02")
            retroCall(map)
        }


    }

    private fun retroCall(map: HashMap<String, String>) {
        val retrofit=Retrofit.Builder().baseUrl(APIConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val call= retrofit.create(RetrofitInterface::class.java)
        call.getWeather(map).enqueue(object :Callback<WeatherResponse>{
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"City Not Found",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherRslt = response.body()!!.weather!![0]
                    weather.text=weatherRslt!!.main+"\n"+weatherRslt.description
                }
            }
        })
    }
}