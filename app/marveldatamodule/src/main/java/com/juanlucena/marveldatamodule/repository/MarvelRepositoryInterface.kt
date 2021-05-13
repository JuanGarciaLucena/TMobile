package com.juanlucena.marveldatamodule.repository

import com.juanlucena.marveldatamodule.models.MarvelCharacterDetailsResponse
import com.juanlucena.marveldatamodule.models.MarvelCharacterListResponse
import retrofit2.Response

interface MarvelRepositoryInterface {
    suspend fun getCharacters(offset: Int) : Response<MarvelCharacterListResponse>
    suspend fun getCharacterDetail(characterId: String) : Response<MarvelCharacterDetailsResponse>
}