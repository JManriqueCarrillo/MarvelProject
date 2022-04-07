package com.jmanrique.marvelproject.app.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.marvelproject.R
import com.jmanrique.marvelproject.databinding.ItemCharacterListBinding
import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacter
import com.jmanrique.marvelproject.utils.extensions.loadUrl
import javax.inject.Inject

class CharacterListAdapter @Inject constructor(private val context: Context) :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    var data: MutableList<MarvelCharacter> = mutableListOf()
    var onItemClick: ((MarvelCharacter) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.ViewHolder = ViewHolder(
        ItemCharacterListBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: CharacterListAdapter.ViewHolder, position: Int) {
        data.get(position).let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: MarvelCharacter) {
            binding.apply {
                character.also {
                    binding.characterListAvatar.loadUrl(
                        "${it.thumbnail.path}/standard_large.${it.thumbnail.extension}",
                        R.drawable.portrait_xlarge
                    )
                    binding.characterListName.text = it.name
                }
            }
        }
    }


}