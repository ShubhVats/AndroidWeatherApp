package com.example.androidweatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidweatherapp.api.NetworkResponse
import com.example.androidweatherapp.api.RetrofitInstance
import com.example.androidweatherapp.api.weatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<weatherModel>>()
    val weatherResult: LiveData<NetworkResponse<weatherModel>> = _weatherResult
    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constants.apiKey, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Succcess(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data.")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Failed to load data.")
            }
        }
    }
}