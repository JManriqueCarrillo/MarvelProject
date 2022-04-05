package com.jmanrique.marvelproject.app.repository

import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataWrapper
import com.jmanrique.marvelproject.data.network.repository.MarvelRemoteStoreImpl
import com.jmanrique.marvelproject.domain.repository.MarvelRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    //private val localStore: MarvelLocalStoreImpl,
    private val remoteStore: MarvelRemoteStoreImpl
) : MarvelRepository {

    override fun getCharacters(offset: Int): Single<CharacterDataWrapper> = remoteStore.getCharacters(offset)
    override fun getCharactersStartWithText(search: String): Single<CharacterDataWrapper> = remoteStore.getCharactersStartWithText(search)
}