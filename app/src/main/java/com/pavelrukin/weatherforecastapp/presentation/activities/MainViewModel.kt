package com.pavelrukin.weatherforecastapp.presentation.activities

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelrukin.weatherforecastapp.data.Weather
import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.domain.usecase.GeocodingByNameUseCase
import com.pavelrukin.weatherforecastapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.format.DateTimeFormatter

class MainViewModel(private val geocodingByNameUseCase: GeocodingByNameUseCase) : ViewModel() {

    init {
        Log.d(javaClass.simpleName, "init: ")
        //  geocodingByName("London")

    }

    var geocodingSateList by mutableStateOf(GeocodingSate(emptyList()))
    var weatherState by mutableStateOf(WeatherState())


    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)

    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        if (newValue.length < 2) {
            geocodingSateList = geocodingSateList.copy(
                weatherInfo = null,
                isLoading = false,
                error = "Malo BUKV"
            )
        } else {
            geocodingByName(name = newValue)
        }
    }

    private fun geocodingByName(name: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.i(javaClass.simpleName, "MainViewModel geocodingByName: $name")
        if (name.length > 2) {
            geocodingSateList =
                when (val result = geocodingByNameUseCase.getGeocodingByName(name = name)) {

                    is Resource.Error ->
                        geocodingSateList.copy(
                            weatherInfo = emptyList(),
                            isLoading = false,
                            error = result.message
                        )
                    is Resource.Success ->
                        geocodingSateList.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )

                }
        }
    }

    fun getFiveDayWeatherForecast(lat: Double, lon: Double) =
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(javaClass.simpleName, "getFiveDayWeatherForecast: ")
            weatherState =
                when (val result = geocodingByNameUseCase.getFiveDayWeatherForecast(lat, lon)) {
                    is Resource.Error -> weatherState.copy(
                        weatherInfo = null,
                        isLoading = false,
                        error = result.message
                    )
                    is Resource.Success -> {
                        result.data?.weatherList?.forEach {
                            Log.d(javaClass.simpleName, "${ it.dateTime.format(
                                DateTimeFormatter.ofPattern("HH:mm")
                            )}: ")

                        }

                        weatherState.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = result.message
                        )
                    }

                }
        }

}

data class GeocodingSate(
    val weatherInfo: List<GeocodingDto>?,
    val isLoading: Boolean = false,
    val error: String? = null
)

data class WeatherState(
    val weatherInfo: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)


enum class SearchWidgetState {
    OPENED,
    CLOSED
}