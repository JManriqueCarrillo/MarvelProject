package com.jmanrique.marvelproject.domain.repository

import com.jmanrique.marvelproject.data.network.model.base.BaseResponse
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getCharacters(): Single<BaseResponse>
}