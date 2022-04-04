package com.jmanrique.marvelproject.domain.repository

import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataWrapper
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getCharacters(offset: Int): Single<CharacterDataWrapper>
}