package com.pavelrukin.weatherforecastapp.data.network.interceptors

import android.content.Context
import com.pavelrukin.weatherforecastapp.utils.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isOnline(context)) {
            throw NoInternet()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}

class NoInternet : IOException() {

    override val message: String?
        get() {
            return "No connectivity exception"
        }

}

