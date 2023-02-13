package com.pavelrukin.weatherforecastapp.data.network.api

import com.pavelrukin.weatherforecastapp.data.models.GeocodingApiResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //Geocoding API

    @GET("/geo/1.0/direct")
    suspend fun getGeocodingByName(
        @Query("q") cityName: String?,
        @Query("limit") limit: Int = 16
        ): ArrayList<GeocodingApiResponseItem>

    /**    api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}
    Parameters
    lat, lon	required	Geographical coordinates (latitude, longitude). If you need the geocoder to automatic convert city names and zip-codes to geo coordinates and the other way around, please use our Geocoding API.
    appid	required	Your unique API key (you can always find it on your account page under the "API key" tab)
    units	optional	Units of measurement. standard, metric and imperial units are available. If you do not use the units parameter, standard units will be applied by default. Learn more
    mode	optional	Response format. JSON format is used by default. To get data in XML format use mode=xml. Learn more
    cnt	optional	A number of timestamps, which will be returned in the API response. Learn more
    units	optional	Units of measurement. standard, metric and imperial units are available. If you do not use the units parameter, standard units will be applied by default. Learn more
    lang	optional	You can use the lang parameter to get the output in your language. Learn more*/
    //TODO Call 5 day / 3 hour forecast data
    //5day weather forecast

    @GET("/data/2.5/forecast")
    fun getFiveDayWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lot: Double
    )
}