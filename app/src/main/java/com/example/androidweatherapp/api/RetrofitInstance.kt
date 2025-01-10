package com.example.androidweatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private const val baseUrl = "https://api.weatherapi.com/"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val weatherApi: WeatherAPI = getInstance().create(WeatherAPI::class.java)
}