package com.jmanrique.marvelproject.domain.repository

import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataWrapper
import com.jmanrique.marvelproject.data.network.model.comic.ComicDataWrapper
import io.reactivex.rxjava3.core.Single

interface DataStore {
    fun getCharacters(offset: Int): Single<CharacterDataWrapper>
    fun getCharactersStartWithText(search: String): Single<CharacterDataWrapper>
    fun getCharacterById(characterId: Int): Single<CharacterDataWrapper>
    fun getComicsByCharacterId(characterId: Int): Single<ComicDataWrapper>
}