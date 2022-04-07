package com.jmanrique.marvelproject.app.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.marvelproject.R
import com.jmanrique.marvelproject.databinding.ItemComicListBinding
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
                    if (comic.urlDetail.isEmpty()) binding.comicDetailsBtn.isEnabled = false
                    else {
                        binding.comicDetailsBtn.isEnabled = true
                        binding.comicDetailsBtn.setOnClickListener { onDetailItemClick?.invoke(comic.urlDetail) }
                    }

                    if (comic.urlPurchase.isEmpty()) binding.comicBuyBtn.isEnabled = false
                    else {
                        binding.comicBuyBtn.isEnabled = true
                        binding.comicBuyBtn.setOnClickListener { onPurchaseItemClick?.invoke(comic.urlPurchase) }
                    }

                    binding.comicImage.loadUrl(
                        "${it.thumbnail.path}/portrait_fantastic.${it.thumbnail.extension}",
                        R.drawable.portrait_xlarge
                    )
                }
            }
        }
    }


}