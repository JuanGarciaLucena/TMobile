package com.juanlucena.tmobile.data.network

import com.juanlucena.tmobile.BuildConfig
import com.juanlucena.tmobile.data.services.MarvelService

object ApiClientProvider {
    fun buildMarvelService(): MarvelService = ApiClient.getClient(BuildConfig.MARVEL_BASE_URL)!!.create(MarvelService::class.java)
}