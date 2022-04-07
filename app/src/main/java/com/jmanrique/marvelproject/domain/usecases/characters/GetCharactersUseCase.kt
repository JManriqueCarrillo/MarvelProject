package com.jmanrique.marvelproject.domain.usecases.characters

import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacterContainer
import com.jmanrique.marvelproject.domain.usecases.SingleUseCase

interface GetCharactersUseCase: SingleUseCase<Int, MarvelCharacterContainer> {
}