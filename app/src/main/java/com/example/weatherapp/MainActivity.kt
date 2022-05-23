package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import com.example.weatherapp.model.WeatherViewModel
import com.example.weatherapp.screens.WeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            WeatherApp {
            WeatherScreen(WeatherViewModel())
            }
        }
    }
}

@Composable
fun WeatherApp(content: @Composable () -> Unit) {
    WeatherAppTheme {
        content()
    }
}
