package com.pavelrukin.weatherforecastapp.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.presentation.activities.GeocodingSate

@Composable
fun GeocodingList(

    geocodingSateList: GeocodingSate,
    onClick: (onClick: GeocodingDto) -> Unit,

    ) {
    LazyColumn(

        modifier = Modifier.wrapContentHeight()


    ) {


        geocodingSateList.weatherInfo.let { data ->
            Log.d(javaClass.simpleName, "MainScreen: $data")

            if (data != null) {

                items(items = data,

                    itemContent = { item ->
                        if (item.state == null) item.state = ""
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                fontSize = 24.sp,
                                text = "${item.name} + ${item.state} ${item.country}",
                                modifier = Modifier.clickable { onClick.invoke(item) }
                            )
                            Divider(
                                startIndent = 8.dp,
                                thickness = 1.dp,
                                color = Color.Black
                            )

                        }


                    })
            }
        }
    }
}

@Composable
@Preview
fun GeocodingListPreview() {

}