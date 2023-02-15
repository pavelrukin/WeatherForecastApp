package com.pavelrukin.weatherforecastapp.presentation.ui.screens

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pavelrukin.weatherforecastapp.R

@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.home)
            )
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
@Preview
fun DefaultAppBarPreview() {
    DefaultAppBar(onSearchClicked = {})
}