package com.example.weatherappretrofitdemo

import com.example.weatherappretrofitdemo.UserResponse.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RetrofitInterface {
    @GET("weather")
    fun getWeather(@QueryMap map: HashMap<String,String>):Call<WeatherResponse>
}