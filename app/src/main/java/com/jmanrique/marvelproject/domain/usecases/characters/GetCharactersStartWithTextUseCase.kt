package com.jmanrique.marvelproject.domain.usecases.characters

import com.jmanrique.marvelproject.domain.model.MarvelCharacterContainer
import com.jmanrique.marvelproject.domain.usecases.SingleUseCase

interface GetCharactersStartWithTextUseCase: SingleUseCase<String, MarvelCharacterContainer> {
}