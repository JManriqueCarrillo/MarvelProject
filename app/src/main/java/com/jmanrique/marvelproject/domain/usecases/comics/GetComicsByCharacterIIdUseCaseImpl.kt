package com.jmanrique.marvelproject.domain.usecases.comics

import com.jmanrique.marvelproject.domain.model.comics.MarvelComicContainer
import com.jmanrique.marvelproject.domain.repository.MarvelRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetComicsByCharacterIIdUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetComicsByCharacterIdUseCase {
    override fun execute(params: Int): Single<MarvelComicContainer> {
        return marvelRepository.getComicsByCharacterId(params).map { wrapper ->
            wrapper.data.toMarvelComicContainer()
        }
    }
}