package com.juanlucena.marveldatamodule.repository

import com.juanlucena.marveldatamodule.network.ApiClientProvider
import com.juanlucena.marveldatamodule.services.MarvelService

class MarvelRepositoryImpl: MarvelRepositoryInterface {

    private val marvelService: MarvelService = ApiClientProvider.buildMarvelService()

    override suspend fun getCharacters(offset: Int) = marvelService.getCharacters(offset)

    override suspend fun getCharacterDetail(characterId: String) = marvelService.getCharacterDetail(characterId)
}