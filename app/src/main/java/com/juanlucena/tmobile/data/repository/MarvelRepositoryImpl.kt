package com.juanlucena.tmobile.data.repository

import com.juanlucena.tmobile.data.network.ApiClientProvider
import com.juanlucena.tmobile.data.services.MarvelService

class MarvelRepositoryImpl: MarvelRepositoryInterface {

    private val marvelService: MarvelService = ApiClientProvider.buildMarvelService()


    override suspend fun getCharacters(offset: Int) = marvelService.getCharacters(offset)

    override suspend fun getCharacterDetail(characterId: String) = marvelService.getCharacterDetail(characterId)
}