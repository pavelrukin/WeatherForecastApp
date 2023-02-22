package com.pavelrukin.weatherforecastapp.presentation.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.pavelrukin.weatherforecastapp.data.network.models.GeocodingDto
import com.pavelrukin.weatherforecastapp.data.network.models.LocalName
import com.pavelrukin.weatherforecastapp.presentation.ui.screens.MainScreen
import com.pavelrukin.weatherforecastapp.presentation.ui.theme.WeatherForecastAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arrayList = arrayListOf<GeocodingDto>(
            GeocodingDto(
                name = "Lviv",
                localName = LocalName(listLocalName = arrayListOf()),
                lat = 0.0,
                lon = 0.0,
                country = "UA",
                state = "UA",
            ),
            GeocodingDto(
                name = "London",
                localName = LocalName(listLocalName = arrayListOf()),
                lat = 0.0,
                lon = 0.0,
                country = "England",
                state = "EU",
            ),

            )
        setContent {
            val viewModel = koinViewModel<MainViewModel>()
            val searchWidgetState by viewModel.searchWidgetState
            val searchTextState by viewModel.searchTextState


            WeatherForecastAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray)
                    ) {
                        MainScreen(
                            viewModel = viewModel,
                            onClick = {
                                viewModel.getFiveDayWeatherForecast(it.lat,it.lon)
                                Toast.makeText(
                                    this@MainActivity,
                                    "click ${it.name}",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            },
                            searchTextState = searchTextState,
                            searchWidgetState = searchWidgetState ,

                        )

                    }
                    if (viewModel.geocodingSateList.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        viewModel.geocodingSateList.error?.let { error ->
                            Text(
                                text = error,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                }


            }

        }


        /*  // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }*/
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherForecastAppTheme {
        Greeting("Android")
    }
}