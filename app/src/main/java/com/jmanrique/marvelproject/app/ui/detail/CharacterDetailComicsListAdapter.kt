package com.jmanrique.marvelproject.app.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.marvelproject.R
import com.jmanrique.marvelproject.databinding.ItemCharacterListBinding
import com.jmanrique.marvelproject.databinding.ItemComicListBinding
import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacter
import com.jmanrique.marvelproject.domain.model.comics.MarvelComic
import com.jmanrique.marvelproject.utils.extensions.loadUrl
import javax.inject.Inject

class CharacterDetailComicsListAdapter @Inject constructor(private val context: Context) :
    RecyclerView.Adapter<CharacterDetailComicsListAdapter.ViewHolder>() {

    var data: MutableList<MarvelComic> = mutableListOf()
    var onPurchaseItemClick: ((String) -> Unit)? = null
    var onDetailItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterDetailComicsListAdapter.ViewHolder = ViewHolder(
        ItemComicListBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(
        holder: CharacterDetailComicsListAdapter.ViewHolder,
        position: Int
    ) {
        data[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemComicListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: MarvelComic) {
            binding.apply {
                comic.also {
                    binding.comicDetailsBtn.setOnClickListener { onDetailItemClick?.invoke(comic.urlDetail) }
                    binding.comicBuyBtn.setOnClickListener { onPurchaseItemClick?.invoke(comic.urlPurchase) }
                    binding.comicImage.loadUrl(
                        "${it.thumbnail.path}/portrait_fantastic.${it.thumbnail.extension}",
                        R.drawable.portrait_xlarge
                    )
                }
            }
        }
    }


}