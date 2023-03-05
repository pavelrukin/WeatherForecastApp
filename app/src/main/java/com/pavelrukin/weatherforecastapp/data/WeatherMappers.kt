package com.pavelrukin.weatherforecastapp.data


import android.util.Log
import com.pavelrukin.weatherforecastapp.data.network.models.WeatherDtoResponse
import com.pavelrukin.weatherforecastapp.domain.weather.WeatherType
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import java.util.*


data class Weather(
    val city: City,
    val weatherList: Map<LocalDate, List<WeatherData>>
)

data class City(
    val name: String,
    val country: String
)

data class WeatherData(
    val dateTime: LocalDateTime,
    val temperature: Double,
    val weatherConditions: WeatherCondition
)

data class WeatherCondition(
    val id: WeatherType,
    val main: String,
    val description: String,
    val icon: String

)

fun WeatherDtoResponse.mapToWeather(): Weather {

    Log.d(javaClass.simpleName, "mapToWeather: ")
    return  Weather(
        city = City(
            name = cityDto.name,
            country = cityDto.country
        ),
        weatherList = weatherDtoLists.map { list ->
            val instant = Instant.ofEpochSecond(list.dt)
            WeatherData(
                dateTime = LocalDateTime.ofInstant(
                    instant,
                    ZoneOffset.UTC
                ), // LocalDateTime.parse(list.dt.toString(), DateTimeFormatter.ISO_DATE_TIME),
                temperature = list.mainDto.temp,
                /*         pressure = list.mainDto.pressure,
                         humidity = list.mainDto.humidity,
                         windSpeed = list.windDto.speed,
                         windDegree = list.windDto.deg,
                         clouds = list.clouds.all,
                         rain = list.rain?.h ?: 0.0,
                         snow = list.snow?.h ?: 0.0,*/
                weatherConditions = list.weatherDto[0].let { weather ->
                    WeatherCondition(
                        id = WeatherType.fromWMO(weather.id),
                        main = weather.main,
                        description = weather.description,
                        icon = weather.icon
                    )
                }
            )
        }.groupBy {  it.dateTime.toLocalDate() }

    )

}

