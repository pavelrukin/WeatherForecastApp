package com.pavelrukin.weatherforecastapp.presentation.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.presentation.activities.MainViewModel
import com.pavelrukin.weatherforecastapp.presentation.activities.SearchWidgetState
import com.pavelrukin.weatherforecastapp.presentation.ui.screens.weather.WeatherCard


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onClick: (onClick: GeocodingDto) -> Unit,


    ) {

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
            Column(
                modifier = Modifier.padding(paddingValues)

            ) {
                val visibilityGeocodingSateList = viewModel.visibilityGeocodingSateList
                val visibilityCityText = viewModel.visibilityCityText
                Log.d(javaClass.simpleName, "MainScreen: visibility $visibilityGeocodingSateList")
                AnimatedVisibility(
                    visible = visibilityGeocodingSateList,
                    enter = fadeIn(
                        // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                        initialAlpha = 0.4f
                    ),
                    exit = fadeOut(
                        // Overwrites the default animation with tween
                        animationSpec = tween(durationMillis = 250)
                    )
                ) {
                    // Content that needs to appear/disappear goes here:
                    GeocodingList(viewModel.geocodingSateList, onClick)
                }
                AnimatedVisibility(
                    visible = visibilityCityText,
                    enter = fadeIn(
                        // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                        initialAlpha = 0.4f
                    ),
                    exit = fadeOut(
                        // Overwrites the default animation with tween
                        animationSpec = tween(durationMillis = 250)
                    )
                ) {
                    // Content that needs to appear/disappear goes here:
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally) // center the box within its parent
                            .background(Color.DarkGray) // set a red background
                    ) {
                        val text = viewModel.weatherState.weatherInfo?.city
                        Text(
                            text = "${text?.name} || ${text?.country}",
                            style = TextStyle(fontSize = 24.sp)
                        )

                    }
                }

                val contents =
                    viewModel.weatherState.weatherInfo?.weatherList
                if (contents != null) {
                    LazyColumn {
                        contents.forEach { (date, weatherDataList) ->
                            stickyHeader {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .background(Color.Blue)
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "${date.month}",
                                        color = Color.White
                                    )
                                    Text(
                                        text = "${date.dayOfWeek.name} ${date.dayOfMonth}",
                                        color = Color.White
                                    )
                                }

                            }
                            item {
                                Row(
                                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    weatherDataList.forEach { whetherData ->
                                        WeatherCard(
                                            backgroundColor = Color.DarkGray,
                                            data = whetherData
                                        )
                                    }

                                }
                            }
                        }
                    }
                }


            }
        }
    )
}


@Composable
@Preview
fun MainScreenPreview() {
    //MainScreen(viewModel = MainViewModel(), onClick = {})
}
