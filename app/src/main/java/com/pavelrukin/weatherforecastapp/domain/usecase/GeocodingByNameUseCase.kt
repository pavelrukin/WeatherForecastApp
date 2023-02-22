package com.pavelrukin.weatherforecastapp.domain.usecase

import android.util.Log
import com.pavelrukin.weatherforecastapp.data.Weather
import com.pavelrukin.weatherforecastapp.data.mapToWeather
import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.domain.repository.WeatherRepository
import com.pavelrukin.weatherforecastapp.utils.Resource

class GeocodingByNameUseCase(private val weatherRepository: WeatherRepository) {


    suspend fun getGeocodingByName(name: String?): Resource<List<GeocodingDto>> {
        return try {
            Resource.Success(
                data = weatherRepository.getGeocodingByName(name = name)
            )
        } catch (e: Exception) {

            Log.e(javaClass.simpleName, "getGeocodingByName: ${e.printStackTrace()}", )

            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }


    suspend fun getFiveDayWeatherForecast(lat: Double, lon: Double): Resource<Weather> {
        return try {
            Resource.Success<Weather>(
                data = weatherRepository.getFiveDayWeatherForecast(lat, lon).mapToWeather()
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }

    }
}
