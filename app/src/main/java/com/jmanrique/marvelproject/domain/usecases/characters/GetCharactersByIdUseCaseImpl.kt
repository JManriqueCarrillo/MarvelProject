package com.jmanrique.marvelproject.domain.usecases.characters

import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacterContainer
import com.jmanrique.marvelproject.domain.repository.MarvelRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharactersByIdUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetCharactersByIdUseCase {
    override fun execute(params: Int): Single<MarvelCharacterContainer> {
        return marvelRepository.getCharacterById(params).map { wrapper ->
            wrapper.data.toMarvelCharacterContainer()
        }
    }
}