package com.pavelrukin.weatherforecastapp.data.network.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit

interface RetrofitFactory {

    fun createRetrofit(gson: Gson): Retrofit

}