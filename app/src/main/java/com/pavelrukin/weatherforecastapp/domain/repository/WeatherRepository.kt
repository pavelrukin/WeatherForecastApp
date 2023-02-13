package com.pavelrukin.weatherforecastapp.domain.repository

import com.pavelrukin.weatherforecastapp.data.models.GeocodingApiResponse
import com.pavelrukin.weatherforecastapp.data.models.GeocodingApiResponseItem
import com.pavelrukin.weatherforecastapp.utils.Resource

interface WeatherRepository {
    suspend fun getGeocodingByName(name: String?): ArrayList<GeocodingApiResponseItem>
}