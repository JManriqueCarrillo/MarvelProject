package com.jmanrique.marvelproject.app.di

import com.jmanrique.marvelproject.domain.usecases.characters.*
import com.jmanrique.marvelproject.domain.usecases.comics.GetComicsByCharacterIIdUseCaseImpl
import com.jmanrique.marvelproject.domain.usecases.comics.GetComicsByCharacterIdUseCase
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

    @Singleton
    @Provides
    fun providesGetCharacterByIdUseCase(impl: GetCharactersByIdUseCaseImpl): GetCharactersByIdUseCase =
        impl

    @Singleton
    @Provides
    fun providesGetComicsByCharacterIdUseCase(impl: GetComicsByCharacterIIdUseCaseImpl): GetComicsByCharacterIdUseCase =
        impl
}