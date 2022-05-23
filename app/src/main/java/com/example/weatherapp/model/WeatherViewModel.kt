package com.example.weatherapp.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var errorMessage: String by mutableStateOf("")
    var weather: Weather? = null /*Weather(main=Main(temp=10.06, temp_min=10.0, temp_max=11.57, humidity=87), wind=Wind(speed=3.6), sys=Sys(sunrise=1652319830, sunset=1652381921), name="Tampere", weather=[Description("", "")])*/

    fun getWeather() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                weather = apiService.getWeather()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}
