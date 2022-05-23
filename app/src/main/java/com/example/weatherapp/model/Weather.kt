package com.example.weatherapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class Weather(
    val main: Main,
    val wind: Wind,
    val sys: Sys,
    var name: String,
    val weather: List<Description>
)

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Int,
)

data class Wind(
    val speed: Double
)

data class Sys(
    val sunrise: Int,
    val sunset: Int
)

data class Description(
    val description: String,
    val icon: String
)

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface APIService {
    @GET("weather?units=metric&appid=289126ef1b80a9dd3fd33c17f6d4035a")
    suspend fun getWeather(@Query("q") city: String): Weather

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService? {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService
        }
    }
}
