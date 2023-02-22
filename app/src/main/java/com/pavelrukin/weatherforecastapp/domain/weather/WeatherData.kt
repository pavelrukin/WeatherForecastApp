package com.pavelrukin.weatherforecastapp.domain.weather

import java.time.LocalDateTime

data class WeatherDatas (
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
