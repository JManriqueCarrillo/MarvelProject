package com.jmanrique.marvelproject.app.di

import com.jmanrique.marvelproject.app.repository.DefaultRepository
import com.jmanrique.marvelproject.data.network.MarvelAPI
import com.jmanrique.marvelproject.data.network.repository.MarvelRemoteStoreImpl
import com.jmanrique.marvelproject.domain.repository.DataStore
import com.jmanrique.marvelproject.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesMarvelRepository(remoteStore: MarvelRemoteStoreImpl): MarvelRepository = DefaultRepository(remoteStore)

    @Singleton
    @Provides
    fun providesRemoteMarvelRepository(api: MarvelAPI): DataStore = MarvelRemoteStoreImpl(api)

}