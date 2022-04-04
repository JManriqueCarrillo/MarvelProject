package com.jmanrique.marvelproject.domain.usecases.characters

import com.jmanrique.marvelproject.data.network.model.base.BaseResponse
import com.jmanrique.marvelproject.domain.repository.MarvelRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
): GetCharactersUseCase {
    override fun execute(params: Void?): Single<BaseResponse> {
        return marvelRepository.getCharacters()
    }
}