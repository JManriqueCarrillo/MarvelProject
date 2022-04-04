package com.jmanrique.marvelproject.domain.usecases.characters

import com.jmanrique.marvelproject.data.network.model.base.BaseResponse
import com.jmanrique.marvelproject.domain.usecases.SingleUseCase

interface GetCharactersUseCase: SingleUseCase<Void, BaseResponse> {
}