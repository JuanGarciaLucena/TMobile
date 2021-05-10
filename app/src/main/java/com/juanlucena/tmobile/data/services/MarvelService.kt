package com.juanlucena.tmobile.data.services

import com.google.gson.JsonObject
import com.juanlucena.tmobile.data.models.MarvelCharacterDetailsResponse
import com.juanlucena.tmobile.data.models.MarvelCharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset: Int): Response<MarvelCharacterListResponse>

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterDetail(@Path("characterId") characterId: String): Response<MarvelCharacterDetailsResponse>

}