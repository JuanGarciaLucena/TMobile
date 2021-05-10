package com.juanlucena.tmobile.data.network

import com.juanlucena.tmobile.data.interceptors.MarvelInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getClient(baseUrl: String): Retrofit?{
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun httpClient(): OkHttpClient{
        return OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .addInterceptor(MarvelInterceptor())
                .build()
    }
}