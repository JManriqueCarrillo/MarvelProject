package com.jmanrique.marvelproject.domain.model.comics

data class MarvelComicContainer(
    val total: Int,
    val results: List<MarvelComic>
)
