package com.jmanrique.marvelproject.domain.usecases.comics

import com.jmanrique.marvelproject.domain.model.comics.MarvelComicContainer
import com.jmanrique.marvelproject.domain.usecases.SingleUseCase

interface GetComicsByCharacterIdUseCase: SingleUseCase<Int, MarvelComicContainer> {
}