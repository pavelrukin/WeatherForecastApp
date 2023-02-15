package com.pavelrukin.weatherforecastapp.presentation.activities

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelrukin.weatherforecastapp.data.models.GeocodingApiResponseItem
import com.pavelrukin.weatherforecastapp.domain.usecase.GeocodingByNameUseCase
import com.pavelrukin.weatherforecastapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val geocodingByNameUseCase: GeocodingByNameUseCase) : ViewModel() {

    init {
        Log.d(javaClass.simpleName, "init: ")
        //  geocodingByName("London")
    }

    var geocodingSateList by mutableStateOf(GeocodingSate(emptyList()))

/*

    private val _list: MutableState<List<GeocodingApiResponseItem>> =
        mutableStateOf(value = emptyList())
    val list: State<List<GeocodingApiResponseItem>> = _list

*/

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
        if (newValue.length<2){
            geocodingSateList =  geocodingSateList.copy(
               weatherInfo = null,
               isLoading = false,
               error = "Malo BUKV"
           )
        }
    }

    fun geocodingByName(name: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.i(javaClass.simpleName, "MainViewModel geocodingByName: $name")
        if (name.length > 2) {
            geocodingSateList = when (val result = geocodingByNameUseCase.getGeocodingByName(name = name)) {
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

}

data class GeocodingSate(
    val weatherInfo: List<GeocodingApiResponseItem>?,
    val isLoading: Boolean = false,
    val error: String? = null
)


enum class SearchWidgetState {
    OPENED,
    CLOSED
}