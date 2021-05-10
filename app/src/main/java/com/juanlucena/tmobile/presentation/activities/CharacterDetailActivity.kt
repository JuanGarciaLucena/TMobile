package com.juanlucena.tmobile.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.juanlucena.tmobile.R
import com.juanlucena.tmobile.common.Utils
import com.juanlucena.tmobile.data.models.MarvelCharacterDetailsResponse
import com.juanlucena.tmobile.viewModels.MarvelViewModel

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var marvelViewModel: MarvelViewModel
    private lateinit var characterDetailContainer: ConstraintLayout
    private lateinit var characterDetailProgressBar: ProgressBar
    private lateinit var characterDetailThumbnail: ImageView
    private lateinit var characterDetailName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        initViews()
        initViewModel()
    }

    private fun initViews(){
        characterDetailContainer = findViewById(R.id.characterDetailContainer)
        characterDetailProgressBar = findViewById(R.id.characterDetailProgressBar)
        characterDetailThumbnail = findViewById(R.id.characterDetailThumbnail)
        characterDetailName = findViewById(R.id.characterDetailName)
    }

    private fun initViewModel(){

        val marvelCharacterId = intent.extras!!.getInt("CHARACTER_ID")

        marvelViewModel = ViewModelProvider(this).get(MarvelViewModel::class.java)
        marvelViewModel.getCharacterDetailSuccessLiveData().observe(this, getCharacterDetailSuccess())
        marvelViewModel.getCharacterDetailFailureLiveData().observe(this, getCharacterDetailFailure())

        if(Utils.isNetworkAvailable(this)) {
            marvelViewModel.getCharacterDetail(marvelCharacterId.toString())
        }else{
            Toast.makeText(this, "No network available", Toast.LENGTH_LONG).show()
        }
    }

    private fun getCharacterDetailSuccess(): Observer<MarvelCharacterDetailsResponse>{
        return Observer { marvelCharacterDetails ->

            val characterData = marvelCharacterDetails.data.results[0]
            characterDetailProgressBar.visibility = View.GONE
            characterDetailContainer.visibility = View.VISIBLE

            characterDetailName.text = characterData.name

            Glide
                .with(this)
                .load(characterData.thumbnail.path + "." + characterData.thumbnail.extension)
                .centerCrop()
                .into(characterDetailThumbnail)
        }
    }

    private fun getCharacterDetailFailure(): Observer<JsonObject>{
        return Observer { exception ->
            Toast.makeText(applicationContext, exception.get("status").asString, Toast.LENGTH_LONG).show()
            finish()
        }
    }
}