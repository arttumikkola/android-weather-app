package com.example.weatherapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var city: String by mutableStateOf("Tampere")
    var weather: Weather? by mutableStateOf(null)

    fun getWeather() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            if(apiService != null) {
                try {
                    weather = apiService.getWeather(city)
                } catch (e: Exception) {
                    errorMessage = e.message.toString()
                }
            }
        }
    }
}
