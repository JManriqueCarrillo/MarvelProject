package com.jmanrique.marvelproject.data.network.repository

import com.jmanrique.marvelproject.data.network.MarvelAPI
import com.jmanrique.marvelproject.data.network.model.base.BaseResponse
import com.jmanrique.marvelproject.domain.repository.DataStore
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MarvelRemoteStoreImpl @Inject constructor(
    private val marvelAPI: MarvelAPI
) : DataStore {

    override fun getCharacters(): Single<BaseResponse> = marvelAPI.getCharacters()
}