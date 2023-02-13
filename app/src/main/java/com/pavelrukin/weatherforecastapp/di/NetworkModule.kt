package com.pavelrukin.weatherforecastapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pavelrukin.weatherforecastapp.data.network.api.WeatherApi
import com.pavelrukin.weatherforecastapp.data.network.interceptors.ResponseInterceptor
import com.pavelrukin.weatherforecastapp.data.network.retrofit.RetrofitFactory
import com.pavelrukin.weatherforecastapp.data.network.retrofit.RetrofitFactoryImpl
import com.pavelrukin.weatherforecastapp.data.repository.WeatherRepositoryImpl
import com.pavelrukin.weatherforecastapp.presentation.activities.MainViewModel
import com.pavelrukin.weatherforecastapp.domain.repository.WeatherRepository
import com.pavelrukin.weatherforecastapp.domain.usecase.GeocodingByNameUseCase
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val dataModules = module {

    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    single { ResponseInterceptor() }

    single<Gson> { GsonBuilder().setLenient().create() }

    single {
        listOf(get<ResponseInterceptor>(), get<HttpLoggingInterceptor>())
    }

    single<RetrofitFactory> { RetrofitFactoryImpl(get()) }
    single { get<RetrofitFactory>().createRetrofit(get()) }
    single { get<Retrofit>().create(WeatherApi::class.java) }


    single<WeatherRepository> { WeatherRepositoryImpl(weatherApi = get()) }
}


val domainModules = module {


    //TODO USE_CASES
    factory { GeocodingByNameUseCase(weatherRepository = get()) }
}


val presentationModule = module {

    //TODO Viewmodels

    viewModel { MainViewModel(get()) }
}

