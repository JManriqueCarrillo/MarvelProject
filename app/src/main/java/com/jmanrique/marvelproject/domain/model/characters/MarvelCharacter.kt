package com.jmanrique.marvelproject.domain.model.characters

import com.jmanrique.marvelproject.data.network.model.common.MarvelImage

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urlDetail: String,
    val thumbnail: MarvelImage,
)