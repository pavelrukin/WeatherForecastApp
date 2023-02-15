package com.pavelrukin.weatherforecastapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pavelrukin.weatherforecastapp.data.models.GeocodingApiResponseItem
import com.pavelrukin.weatherforecastapp.presentation.activities.MainViewModel
import com.pavelrukin.weatherforecastapp.presentation.activities.SearchWidgetState

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onClick: (onClick: GeocodingApiResponseItem) -> Unit

) {

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState


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
                    viewModel.geocodingByName(it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        content = { paddingValues ->
            Column(Modifier.padding(paddingValues)) {
                GeocodingList(viewModel.geocodingSateList, onClick)
                Text("text")
            }
        }
    )
}

@Composable
@Preview
fun MainScreenPreview() {
    //MainScreen(viewModel = MainViewModel(), onClick = {})
}
