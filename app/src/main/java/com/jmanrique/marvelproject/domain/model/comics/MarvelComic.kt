package com.jmanrique.marvelproject.domain.model.comics

import com.jmanrique.marvelproject.data.network.model.common.MarvelImage

data class MarvelComic(
    val id: Int,
    val title: String,
    val description: String,
    val previewText: String,
    val urlDetail: String,
    val urlPurchase: String,
    val thumbnail: MarvelImage,
    val images: List<MarvelImage>
)
