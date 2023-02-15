package com.pavelrukin.weatherforecastapp.data.network.interceptors


import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class ResponseInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("appid", "73bf035b4b2eb3b787ae48fadc4a27b7")
            .build()
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}



