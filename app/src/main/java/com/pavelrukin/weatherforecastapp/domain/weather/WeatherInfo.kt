package com.pavelrukin.weatherforecastapp.domain.weather
import com.google.gson.annotations.SerializedName

/*
data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)*/
/*

data class WeatherDto(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("base")
    val base: String, // stations
    @SerializedName("main")
    val main: Main,
    @SerializedName("visibility")
    val visibility: Int, // 10000
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Int, // 1676578538
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int, // 3600
    @SerializedName("id")
    val id: Int, // 3163858
    @SerializedName("name")
    val name: String, // Zocca
    @SerializedName("cod")
    val cod: Int // 200
)

data class Coord(
    @SerializedName("lon")
    val lon: Double, // 10.99
    @SerializedName("lat")
    val lat: Double // 44.34
)

data class Weather(
    @SerializedName("id")
    val id: Int, // 802
    @SerializedName("main")
    val main: String, // Clouds
    @SerializedName("description")
    val description: String, // scattered clouds
    @SerializedName("icon")
    val icon: String // 03n
)

data class Main(
    @SerializedName("temp")
    val temp: Double, // 277.62
    @SerializedName("feels_like")
    val feelsLike: Double, // 275.64
    @SerializedName("temp_min")
    val tempMin: Double, // 275.49
    @SerializedName("temp_max")
    val tempMax: Double, // 278.75
    @SerializedName("pressure")
    val pressure: Int, // 1030
    @SerializedName("humidity")
    val humidity: Int, // 79
    @SerializedName("sea_level")
    val seaLevel: Int, // 1030
    @SerializedName("grnd_level")
    val grndLevel: Int // 941
)

data class Wind(
    @SerializedName("speed")
    val speed: Double, // 2.26
    @SerializedName("deg")
    val deg: Int, // 200
    @SerializedName("gust")
    val gust: Double // 3.2
)

data class Clouds(
    @SerializedName("all")
    val all: Int // 38
)

data class Sys(
    @SerializedName("type")
    val type: Int, // 2
    @SerializedName("id")
    val id: Int, // 2004688
    @SerializedName("country")
    val country: String, // IT
    @SerializedName("sunrise")
    val sunrise: Int, // 1676528114
    @SerializedName("sunset")
    val sunset: Int // 1676565918
)*/
