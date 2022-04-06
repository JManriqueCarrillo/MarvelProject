package com.jmanrique.marvelproject.domain.model.characters

data class MarvelCharacterContainer(
    val total: Int,
    val results: List<MarvelCharacter>
)