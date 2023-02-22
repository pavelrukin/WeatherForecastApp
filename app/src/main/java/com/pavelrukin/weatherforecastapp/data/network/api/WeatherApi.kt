package com.pavelrukin.weatherforecastapp.data.network.api

import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.data.network.models.WeatherDtoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //Geocoding API

    @GET("/geo/1.0/direct")
    suspend fun getGeocodingByName(
        @Query("q") cityName: String?,
        @Query("limit") limit: Int = 16
    ): ArrayList<GeocodingDto>

    @GET("/data/2.5/forecast")
    suspend fun getFiveDayWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lot: Double
    ): WeatherDtoResponse
}