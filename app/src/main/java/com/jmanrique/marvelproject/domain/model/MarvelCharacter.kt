package com.jmanrique.marvelproject.domain.model

import com.jmanrique.marvelproject.data.network.model.common.Thumbnail
import com.jmanrique.marvelproject.data.network.model.common.Url

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Thumbnail,
)