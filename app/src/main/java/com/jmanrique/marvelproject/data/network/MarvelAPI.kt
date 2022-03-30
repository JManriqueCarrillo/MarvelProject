package com.jmanrique.marvelproject.data.network

import com.jmanrique.marvelproject.data.network.model.base.BaseResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MarvelAPI {

    //Fetches lists of characters.
    @GET("@/v1/public/characters")
    fun getCharacters(): Single<BaseResponse>

}