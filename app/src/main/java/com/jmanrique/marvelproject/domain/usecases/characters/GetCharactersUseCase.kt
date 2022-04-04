package com.jmanrique.marvelproject.domain.usecases.characters

import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataWrapper
import com.jmanrique.marvelproject.domain.usecases.SingleUseCase

interface GetCharactersUseCase: SingleUseCase<Int, CharacterDataWrapper> {
}