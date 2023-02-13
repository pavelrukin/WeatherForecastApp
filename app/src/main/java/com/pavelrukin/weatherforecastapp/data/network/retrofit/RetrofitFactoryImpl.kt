package com.pavelrukin.weatherforecastapp.data.network.retrofit

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val BASE_URL = "https://api.openweathermap.org/"

class RetrofitFactoryImpl(
    private val interceptors: List<Interceptor>,

    ) : RetrofitFactory {

    companion object {
        private const val TIMEOUT_SECONDS: Long = 60
    }

    override fun createRetrofit(gson: Gson): Retrofit {
        val client = OkHttpClient().newBuilder()
            .apply {
                interceptors.forEach { addInterceptor(it) }
            }
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}