package com.jmanrique.marvelproject.app.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmanrique.marvelproject.R
import com.jmanrique.marvelproject.data.network.model.characters.Character
import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataContainer
import com.jmanrique.marvelproject.databinding.ItemCharacterListBinding
import com.jmanrique.marvelproject.utils.extensions.loadUrl
import javax.inject.Inject

class CharacterListAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    lateinit var data: CharacterDataContainer
    var onItemClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.ViewHolder = ViewHolder(
        ItemCharacterListBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: CharacterListAdapter.ViewHolder, position: Int) {
        data.results?.get(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(data.results!![position])
        }
    }

    override fun getItemCount(): Int = data.results?.let { data.results!!.size } ?: run { 0 }

    inner class ViewHolder(private val binding: ItemCharacterListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(character: Character){
            binding.apply {
                character.also {
                    binding.characterListAvatar.loadUrl("${it.thumbnail?.path}/portrait_xlarge.${it.thumbnail?.extension}", R.drawable.portrait_xlarge)
                    binding.characterListName.text = it.name
                }
            }
        }
    }


}