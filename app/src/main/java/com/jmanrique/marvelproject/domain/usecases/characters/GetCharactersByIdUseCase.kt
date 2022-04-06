package com.jmanrique.marvelproject.domain.usecases.characters

import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacterContainer
import com.jmanrique.marvelproject.domain.usecases.SingleUseCase

interface GetCharactersByIdUseCase: SingleUseCase<Int, MarvelCharacterContainer> {
}