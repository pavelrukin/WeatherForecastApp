package com.pavelrukin.weatherforecastapp.data.repository

import com.pavelrukin.weatherforecastapp.data.network.api.WeatherApi
import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.data.network.models.WeatherDtoResponse
import com.pavelrukin.weatherforecastapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {


    override suspend fun getGeocodingByName(name: String?): ArrayList<GeocodingDto> {

        return weatherApi.getGeocodingByName(name)

    }

    override suspend fun getFiveDayWeatherForecast(lat: Double, lon: Double): WeatherDtoResponse {
        return weatherApi.getFiveDayWeatherForecast(lat, lon)
    }
}



