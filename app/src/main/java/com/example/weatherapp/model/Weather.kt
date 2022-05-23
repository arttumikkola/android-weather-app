package com.example.weatherapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Weather(
    val temp: Double,
    val description: String,
    val icon: String,
    val windSpeed: Double,
    val rainChance: Double,
    val humidity: Int,
    val sunrise: Int,
    val sunset: Int
)
const val BASE_URL = "https://openweathermap.org/"

interface APIService {
    @GET("")
    suspend fun getWeather(): List<Weather>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}