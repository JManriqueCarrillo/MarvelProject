package com.jmanrique.marvelproject.data.network.repository

import com.jmanrique.marvelproject.data.network.MarvelAPI
import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataWrapper
import com.jmanrique.marvelproject.data.network.model.comic.ComicDataWrapper
import com.jmanrique.marvelproject.domain.repository.DataStore
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MarvelRemoteStoreImpl @Inject constructor(
    private val marvelAPI: MarvelAPI
) : DataStore {

    override fun getCharacters(offset: Int): Single<CharacterDataWrapper> =
        marvelAPI.getCharacters(offset.toString())

    override fun getCharactersStartWithText(
        search: String
    ): Single<CharacterDataWrapper> =
        marvelAPI.getCharactersStartWithText(search)

    override fun getCharacterById(characterId: Int): Single<CharacterDataWrapper> =
        marvelAPI.getCharacterById(characterId)

    override fun getComicsByCharacterId(characterId: Int): Single<ComicDataWrapper> =
        marvelAPI.getComicsByCharacterId(characterId)
}