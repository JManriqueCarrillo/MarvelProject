package com.jmanrique.marvelproject.data.network

import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MarvelAPI {

    //Fetches lists of characters.
    @GET("characters")
    fun getCharacters(): Single<CharacterDataWrapper>

}