package com.juanlucena.tmobile.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class MarvelInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val httpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", "1")
                .addQueryParameter("apikey", "d483d4f746f151f0285e25495c5b1537")
                .addQueryParameter("hash", "3ceaa8196bf565006e58af62f0e68523")
                .build()

        val requestBuilder = originalRequest.newBuilder()
                .url(httpUrl)
                .build()

        return chain.proceed(requestBuilder)
    }


}