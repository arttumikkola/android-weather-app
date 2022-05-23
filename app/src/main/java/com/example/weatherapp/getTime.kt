package com.example.weatherapp

import java.text.SimpleDateFormat
import java.util.*

fun getTime(s: String): String {
    try {
        val sdf = SimpleDateFormat("HH:mm")
        val netDate = Date(s.toLong() * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}