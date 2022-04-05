package com.jmanrique.marvelproject.domain.usecases.characters

import com.jmanrique.marvelproject.domain.model.MarvelCharacterContainer
import com.jmanrique.marvelproject.domain.repository.MarvelRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharactersStartWithTextUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetCharactersStartWithTextUseCase {
    override fun execute(params: String): Single<MarvelCharacterContainer> {
        return marvelRepository.getCharactersStartWithText(params).map { wrapper ->
            wrapper.data.toMarvelCharacterContainer()
        }
    }
}