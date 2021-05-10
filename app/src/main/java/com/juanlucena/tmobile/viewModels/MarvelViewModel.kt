package com.juanlucena.tmobile.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.juanlucena.tmobile.data.models.MarvelCharacterDetailsResponse
import com.juanlucena.tmobile.data.models.MarvelCharacterListResponse
import com.juanlucena.tmobile.data.repository.MarvelRepositoryImpl
import kotlinx.coroutines.*

class MarvelViewModel: ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    private val marvelRepository = MarvelRepositoryImpl()

    private val _getCharactersSuccess = MutableLiveData<MarvelCharacterListResponse>()
    private val _getCharactersFailure = MutableLiveData<JsonObject>()

    private val _getCharacterDetailSuccessLiveData = MutableLiveData<MarvelCharacterDetailsResponse>()
    private val _getCharacterDetailFailureLiveData = MutableLiveData<JsonObject>()

    private val getCharactersSuccess: LiveData<MarvelCharacterListResponse>
        get() = _getCharactersSuccess
    private val getCharactersFailure: LiveData<JsonObject>
        get() = _getCharactersFailure

    private val getCharacterDetailSuccess: LiveData<MarvelCharacterDetailsResponse>
        get() = _getCharacterDetailSuccessLiveData
    private val getCharacterDetailFailure: LiveData<JsonObject>
        get() = _getCharacterDetailFailureLiveData

    fun getCharacters(offset: Int){
        coroutineScope.launch {
            val charactersResponse = marvelRepository.getCharacters(offset)
            withContext(Dispatchers.Main){
                if(charactersResponse.isSuccessful){
                    _getCharactersSuccess.value = charactersResponse.body()
                }else{
                    _getCharactersFailure.value = Gson().fromJson(charactersResponse.errorBody()!!.string(), JsonObject::class.java)
                }
            }
        }
    }

    fun getCharacterDetail(characterId: String){
        coroutineScope.launch {
            val characterDetailResponse = marvelRepository.getCharacterDetail(characterId)
            withContext(Dispatchers.Main){
                if(characterDetailResponse.isSuccessful){
                    _getCharacterDetailSuccessLiveData.value = characterDetailResponse.body()
                }else{
                    _getCharacterDetailFailureLiveData.value = Gson().fromJson(characterDetailResponse.errorBody()!!.string(), JsonObject::class.java)
                }
            }
        }
    }

    fun getCharactersSuccessLiveData() = getCharactersSuccess
    fun getCharactersFailureLiveData() = getCharactersFailure

    fun getCharacterDetailSuccessLiveData() = getCharacterDetailSuccess
    fun getCharacterDetailFailureLiveData() = getCharacterDetailFailure
}