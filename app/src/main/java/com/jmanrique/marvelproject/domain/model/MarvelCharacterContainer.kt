package com.jmanrique.marvelproject.domain.model

data class MarvelCharacterContainer(
    val total: Int,
    val results: List<MarvelCharacter>
)