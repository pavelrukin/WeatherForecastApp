package com.pavelrukin.weatherforecastapp.data.network.models

import com.google.gson.annotations.SerializedName

data class GeocodingApiResponse(val geocodingApiResponseItems: List<GeocodingDto>)

data class GeocodingDto(
    @SerializedName("name")
    val name: String, // Kyiv
    @SerializedName("local_names")
    val localName: LocalName,
    @SerializedName("lat")
    val lat: Double, // 50.4500336
    @SerializedName("lon")
    val lon: Double, // 30.5241361
    @SerializedName("country")
    val country: String, // UA
    @SerializedName("state")
    var state: String?  // Mykolaiv Oblast
)

data class LocalName(val listLocalName: ArrayList<String>)




