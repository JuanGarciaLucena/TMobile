package com.juanlucena.tmobile.data.repository

import com.google.gson.JsonObject
import com.juanlucena.tmobile.data.models.MarvelCharacterDetailsResponse
import com.juanlucena.tmobile.data.models.MarvelCharacterListResponse
import retrofit2.Response

interface MarvelRepositoryInterface {
    suspend fun getCharacters(offset: Int) : Response<MarvelCharacterListResponse>
    suspend fun getCharacterDetail(characterId: String) : Response<MarvelCharacterDetailsResponse>
}