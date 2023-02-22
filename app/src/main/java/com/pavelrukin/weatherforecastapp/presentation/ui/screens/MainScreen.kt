package com.pavelrukin.weatherforecastapp.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.presentation.activities.MainViewModel
import com.pavelrukin.weatherforecastapp.presentation.activities.SearchWidgetState
import com.pavelrukin.weatherforecastapp.presentation.ui.screens.weather.WeatherCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onClick: (onClick: GeocodingDto) -> Unit,


    ) {
/*
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState*/


    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    viewModel.updateSearchTextState(newValue = it)

                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        content = { paddingValues ->
            Column(Modifier.padding(paddingValues)) {
                GeocodingList(viewModel.geocodingSateList, onClick)
                Text(text = "${viewModel.weatherState.weatherInfo?.city}")

                LazyColumn(content = {
                    viewModel.weatherState.weatherInfo?.weatherList.let { data ->
                        if (data != null)
                            items(items = data,
                                itemContent = {
                                    WeatherCard(backgroundColor = Color.DarkGray, data = it )
                                }
                            )
                    }
                })
                /*     WeatherCard(

                         backgroundColor = Color.DarkGray
                     )*/
                Spacer(modifier = Modifier.height(16.dp))
                //   WeatherForecast(state = viewModel.weatherState)
            }
        }
    )
}

@Composable
@Preview
fun MainScreenPreview() {
    //MainScreen(viewModel = MainViewModel(), onClick = {})
}
