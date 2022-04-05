package com.jmanrique.marvelproject.app.di

import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersStartWithTextUseCase
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersStartWithTextUseCaseImpl
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersUseCase
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetCharactersUseCase(impl: GetCharactersUseCaseImpl): GetCharactersUseCase = impl

    @Singleton
    @Provides
    fun providesGetCharacterStartWithTextUseCase(impl: GetCharactersStartWithTextUseCaseImpl): GetCharactersStartWithTextUseCase =
        impl

}