package com.pavelrukin.weatherforecastapp.data.network.models
import com.google.gson.annotations.SerializedName

data class WeatherDtoResponse(
    @SerializedName("cod")
    val cod: String, // 200
    @SerializedName("message")
    val message: Int, // 0
    @SerializedName("cnt")
    val cnt: Int, // 40
    @SerializedName("list")
    val weatherDtoLists: List<WeatherDtoList>,
    @SerializedName("city")
    val cityDto: CityDto
)

data class WeatherDtoList (
    @SerializedName("dt")
    val dt: Long, // 1676656800
    @SerializedName("main")
    val mainDto: MainDto,
    @SerializedName("weather")
    val weatherDto: List<WeatherDto>,
    @SerializedName("clouds")
    val clouds: CloudsDto,
    @SerializedName("wind")
    val windDto: WindDto,
    @SerializedName("visibility")
    val visibility: Int, // 10000
    @SerializedName("pop")
    val pop: Double, // 0.25
    @SerializedName("sys")
    val sys: SysDto,
    @SerializedName("dt_txt")
    val dtTxt: String, // 2023-02-17 18:00:00
    @SerializedName("snow")
    val snow: SnowDto,
    @SerializedName("rain")
    val rain: RainDto
)

data class CityDto(
    @SerializedName("id")
    val id: Int, // 696050
    @SerializedName("name")
    val name: String, // Pushcha-Vodytsya
    @SerializedName("coord")
    val coord: CoordDto,
    @SerializedName("country")
    val country: String, // UA
    @SerializedName("population")
    val population: Int, // 0
    @SerializedName("timezone")
    val timezone: Int, // 7200
    @SerializedName("sunrise")
    val sunrise: Int, // 1676610413
    @SerializedName("sunset")
    val sunset: Int // 1676647039
)

data class MainDto(
    @SerializedName("temp")
    val temp: Double, // 273.7
    @SerializedName("feels_like")
    val feelsLike: Double, // 270.69
    @SerializedName("temp_min")
    val tempMin: Double, // 273.34
    @SerializedName("temp_max")
    val tempMax: Double, // 273.7
    @SerializedName("pressure")
    val pressure: Int, // 1017
    @SerializedName("sea_level")
    val seaLevel: Int, // 1017
    @SerializedName("grnd_level")
    val grndLevel: Int, // 997
    @SerializedName("humidity")
    val humidity: Int, // 87
    @SerializedName("temp_kf")
    val tempKf: Double // 0.36
)

data class WeatherDto(
    @SerializedName("id")
    val id: Int, // 804
    @SerializedName("main")
    val main: String, // Clouds
    @SerializedName("description")
    val description: String, // overcast clouds
    @SerializedName("icon")
    val icon: String // 04n
)

data class CloudsDto(
    @SerializedName("all")
    val all: Int // 100
)

data class WindDto(
    @SerializedName("speed")
    val speed: Double, // 2.59
    @SerializedName("deg")
    val deg: Int, // 155
    @SerializedName("gust")
    val gust: Double // 5.53
)

data class SysDto(
    @SerializedName("pod")
    val pod: String // n
)

data class SnowDto(
    @SerializedName("3h")
    val h: Double // 2.18
)

data class RainDto(
    @SerializedName("3h")
    val h: Double // 2.52
)

data class CoordDto(
    @SerializedName("lat")
    val lat: Double, // 50.45
    @SerializedName("lon")
    val lon: Double // 30.52
)


