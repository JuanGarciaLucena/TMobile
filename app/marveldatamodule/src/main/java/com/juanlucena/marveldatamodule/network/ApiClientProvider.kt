package com.juanlucena.marveldatamodule.network

import com.juanlucena.marveldatamodule.BuildConfig
import com.juanlucena.marveldatamodule.services.MarvelService

object ApiClientProvider {
    fun buildMarvelService(): MarvelService = ApiClient.getClient(BuildConfig.MARVEL_BASE_URL)!!.create(
        MarvelService::class.java)
}