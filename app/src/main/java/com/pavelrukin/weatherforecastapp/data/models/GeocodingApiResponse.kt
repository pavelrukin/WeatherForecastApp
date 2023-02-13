package com.pavelrukin.weatherforecastapp.data.models

data class GeocodingApiResponse(val geocodingApiResponseItems: List<GeocodingApiResponseItem>)

data class GeocodingApiResponseItem(
    val name: String, // Kyiv
    val local_names: LocalName,
    val lat: Double, // 50.4500336
    val lon: Double, // 30.5241361
    val country: String, // UA
    val state: String? // Mykolaiv Oblast
)
data class LocalName(val listLocalName:ArrayList<String>)




