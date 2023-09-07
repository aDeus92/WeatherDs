package com.example.weatherds.data

data class WeatherModule(
    val city: String,
    val timeLast: String,
    val currentTemp:String,
    val condition:String,
    val icon:String,
    val maxTemp:String,
    val minTemp:String,
    val hours: String
)
