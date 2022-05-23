package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.getTime
import com.example.weatherapp.model.WeatherViewModel
import java.math.RoundingMode
import java.util.*

@Composable
fun WeatherScreen(vm: WeatherViewModel) {
    LaunchedEffect(Unit, block = {
        vm.getWeather()
    })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(165, 226, 255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(35.dp),
                horizontalAlignment = Alignment.End
            ) {
            }
            Column(
                modifier = Modifier
                    .width(280.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                TextField(vm)
            }
            Column(
                modifier = Modifier
                    .width(40.dp)
                    .height(50.dp)
                    .padding(3.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
            }
        }
        vm.weather?.main?.temp?.toBigDecimal()?.setScale(1, RoundingMode.UP)?.let {
            currentWeather(
                it.toDouble(),
                vm.weather!!.weather[0].description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                vm.weather!!.wind.speed.toBigDecimal().setScale(1, RoundingMode.UP).toDouble(),
                getTime(vm.weather!!.sys.sunrise.toString()),
                getTime(vm.weather!!.sys.sunset.toString()),
                vm.weather!!.main.humidity, vm.weather!!.weather[0].icon
            )
        }
    }
}

@Composable
fun currentWeather(
    temp: Double,
    description: String,
    windSpeed: Double,
    sunrise: String,
    sunset: String,
    humidity: Int,
    icon: String
) {
    WeatherImage(icon)
    Text(
        modifier = Modifier.padding(5.dp),
        text = description,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.onSurface
    )
    Text(
        modifier = Modifier.padding(5.dp),
        text = "$temp°C",
        textAlign = TextAlign.Left,
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.onSurface,
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .height(50.dp),
        ) {
            Row(
                modifier = Modifier
                    .height(50.dp)
            ) {
                Image(
                    modifier = Modifier
                        .height(35.dp)
                        .width(35.dp)
                        .padding(5.dp),
                    painter = painterResource(id = R.drawable.sunrise),
                    contentDescription = "Sunrise icon",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "$sunrise",
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
                Image(
                    modifier = Modifier
                        .height(35.dp)
                        .width(35.dp)
                        .padding(5.dp),
                    painter = painterResource(id = R.drawable.sunset),
                    contentDescription = "Sunset icon",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "$sunset",
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .height(50.dp),
        ) {
            Row(
                modifier = Modifier
                    .height(50.dp)
            ) {
                Image(
                    modifier = Modifier
                        .height(35.dp)
                        .width(35.dp)
                        .padding(5.dp),
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = "Wind icon",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(6.dp),
                    text = windSpeed.toString() + "m/s",
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(0.dp)
                .height(50.dp),
        ) {
            Row(
                modifier = Modifier
                    .height(50.dp)
            ) {
                Image(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .padding(5.dp),
                    painter = painterResource(id = R.drawable.water),
                    contentDescription = "Rain icon",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "$humidity%",
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextField(weatherViewModel: WeatherViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val kc = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = Modifier
            .padding(10.dp),
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text(text = "Enter city") },
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            weatherViewModel.city = text.text
            weatherViewModel.getWeather()
            kc?.hide()
        })
    )
}

@Composable
fun WeatherImage(icon: String) {
    Image(
        modifier = Modifier
            .height(240.dp)
            .width(240.dp)
            .padding(0.dp),
        painter = painterResource(
            id = (if (icon == "01d") {
                R.drawable.sunny
            } else if (icon == "01n") {
                R.drawable.moon
            } else if (icon == "02d") {
                R.drawable.cloudy
            } else if (icon == "02n") {
                R.drawable.cloudy_moon
            } else if (icon == "03d" || icon == "03n") {
                R.drawable.cloudy2
            } else if (icon == "04d" || icon == "04n") {
                R.drawable.cloudy2
            } else if (icon == "09d" || icon == "09n") {
                R.drawable.rain
            } else if (icon == "10d" || icon == "10n") {
                R.drawable.rain
            } else if (icon == "11d" || icon == "11n") {
                R.drawable.storm
            } else if (icon == "13d" || icon == "13n") {
                R.drawable.snowfall
            } else if (icon == "50d" || icon == "50n") {
                R.drawable.fog
            } else {
                R.drawable.cloudy
            })
        ),
        contentDescription = "Weather image",
        contentScale = ContentScale.Crop
    )
}