package com.example.androidweatherapp.api

sealed class NetworkResponse<out T> {
    data class Succcess<out T>(val data : T) : NetworkResponse<T>()
    data class Error(val message : String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}