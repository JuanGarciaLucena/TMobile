package com.juanlucena.tmobile.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanlucena.tmobile.R
import com.juanlucena.marveldatamodule.models.MarvelCharacter

class CharactersAdapter(
    private val context: Context,
    private val marvelCharacterList: List<MarvelCharacter>,
    private val onItemClick: (MarvelCharacter) -> Unit): RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item_layout, parent, false)
        return CharacterViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = marvelCharacterList[position]
        holder.bind(context, character)
    }

    override fun getItemCount() = marvelCharacterList.size

    class CharacterViewHolder(itemView: View, val onClick: (MarvelCharacter) -> Unit): RecyclerView.ViewHolder(itemView){
        private val characterNameLabel: TextView = itemView.findViewById(R.id.characterNameLabel)
        private val characterThumbnail: ImageView = itemView.findViewById(R.id.characterThumbnail)
        private var currentMarvelCharacter: MarvelCharacter? = null

        init {
            itemView.setOnClickListener {
                currentMarvelCharacter?.let{
                    onClick(it)
                }
            }
        }

        fun bind(context: Context, marvelCharacter: MarvelCharacter){
            currentMarvelCharacter = marvelCharacter
            characterNameLabel.text = marvelCharacter.name

            Glide
                .with(context)
                .load(marvelCharacter.thumbnail.path + "." + marvelCharacter.thumbnail.extension)
                .centerCrop()
                .into(characterThumbnail)
        }
    }
}