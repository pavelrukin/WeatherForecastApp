package com.pavelrukin.weatherforecastapp.domain.usecase

import com.pavelrukin.weatherforecastapp.data.models.GeocodingApiResponseItem
import com.pavelrukin.weatherforecastapp.domain.repository.WeatherRepository
import com.pavelrukin.weatherforecastapp.utils.Resource
import retrofit2.HttpException

class GeocodingByNameUseCase(private val weatherRepository: WeatherRepository) {


    suspend fun getGeocodingByName(name: String?): Resource<List<GeocodingApiResponseItem>> {
        return try {
            Resource.Success(
                data = weatherRepository.getGeocodingByName(name = name)
            )
        } catch (e: Exception) {

            e.printStackTrace()

            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}
