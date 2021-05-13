package com.juanlucena.tmobile.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.juanlucena.tmobile.R
import com.juanlucena.tmobile.common.Constants.Companion.CHARACTER_ID
import com.juanlucena.tmobile.common.Utils
import com.juanlucena.marveldatamodule.models.MarvelCharacter
import com.juanlucena.marveldatamodule.models.MarvelCharacterListResponse
import com.juanlucena.tmobile.presentation.adapters.CharactersAdapter
import com.juanlucena.tmobile.viewModels.MarvelViewModel
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    //private lateinit var marvelViewModel: MarvelViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var characterRecyclerView: RecyclerView
    private lateinit var viewManager: GridLayoutManager
    private lateinit var retryButton: Button

    private var offset = 0
    private var marvelCharacterList = ArrayList<MarvelCharacter>()

    val marvelViewModel by inject<MarvelViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()
        initListeners()
    }

    private fun initViews(){
        progressBar = findViewById(R.id.progressBar)
        characterRecyclerView = findViewById(R.id.characterList)
        retryButton = findViewById(R.id.retryButton)
        viewManager = GridLayoutManager(this, 2)
        marvelCharacterList.clear()
    }

    private fun initViewModel(){
        marvelViewModel.getCharactersSuccessLiveData().observe(this, getCharactersSuccess())
        marvelViewModel.getCharactersFailureLiveData().observe(this, getCharactersFailure())

        if(Utils.isNetworkAvailable(this)) {
            marvelViewModel.getCharacters(0)
        }else{
            Toast.makeText(this, getString(R.string.errorNoNetwork), Toast.LENGTH_LONG).show()
        }
    }

    private fun initListeners() {
        characterRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    progressBar.visibility = View.VISIBLE
                    offset += 20

                    if (Utils.isNetworkAvailable(this@MainActivity)) {
                        marvelViewModel.getCharacters(offset)
                    } else {
                        Toast.makeText(this@MainActivity, getString(R.string.errorNoNetwork), Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        retryButton.setOnClickListener {
            retryButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            marvelViewModel.getCharacters(0)
        }
    }

    private fun initCharacterList(characterList: List<MarvelCharacter>){

        marvelCharacterList.addAll(characterList)

        val charactersAdapter = CharactersAdapter(this, marvelCharacterList){ item ->
            val intent = Intent(this, CharacterDetailActivity::class.java)
            intent.putExtra(CHARACTER_ID, item.id)
            startActivity(intent)
        }

        if(characterRecyclerView.adapter == null) {
            characterRecyclerView.setHasFixedSize(true)
            characterRecyclerView.layoutManager = viewManager
            characterRecyclerView.adapter = charactersAdapter
        }else {
            characterRecyclerView.adapter!!.notifyDataSetChanged()
        }
    }

    private fun getCharactersSuccess(): Observer<MarvelCharacterListResponse> {
        return Observer { charactersResponse ->
            progressBar.visibility = View.GONE
            retryButton.visibility = View.GONE
            initCharacterList(charactersResponse.data.results)
            characterRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun getCharactersFailure(): Observer<JsonObject>{
        return Observer { exception ->
            progressBar.visibility = View.GONE
            retryButton.visibility = View.VISIBLE
            Toast.makeText(applicationContext, exception.get("status").asString, Toast.LENGTH_LONG).show()
        }
    }
}