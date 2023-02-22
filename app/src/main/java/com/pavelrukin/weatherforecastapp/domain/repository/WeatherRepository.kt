package com.pavelrukin.weatherforecastapp.domain.repository

import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.data.network.models.WeatherDtoResponse


interface WeatherRepository {
    suspend fun getGeocodingByName(name: String?): ArrayList<GeocodingDto>

    suspend fun getFiveDayWeatherForecast(lat: Double, lon: Double): WeatherDtoResponse
}