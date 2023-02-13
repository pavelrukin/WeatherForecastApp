package com.pavelrukin.weatherforecastapp.data.repository

import com.pavelrukin.weatherforecastapp.data.models.GeocodingApiResponse
import com.pavelrukin.weatherforecastapp.data.models.GeocodingApiResponseItem
import com.pavelrukin.weatherforecastapp.data.network.api.WeatherApi
import com.pavelrukin.weatherforecastapp.domain.repository.WeatherRepository
import com.pavelrukin.weatherforecastapp.utils.Resource

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {


    override suspend fun getGeocodingByName(name: String?): ArrayList<GeocodingApiResponseItem> {

        return  weatherApi.getGeocodingByName(name)

    }
}



