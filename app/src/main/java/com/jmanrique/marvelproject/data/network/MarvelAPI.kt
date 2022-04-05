package com.jmanrique.marvelproject.data.network

import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataWrapper
import com.jmanrique.marvelproject.data.network.model.comic.ComicDataWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    //Fetches lists of characters.
    @GET("characters")
    fun getCharacters(@Query("offset") offset: String): Single<CharacterDataWrapper>

    @GET("characters")
    fun getCharactersStartWithText(
        @Query("nameStartsWith") search: String
    ): Single<CharacterDataWrapper>

    @GET("characters/{characterId}/comics")
    fun getComicsByCharacterId(
        @Path("characterId") characterId: Int
    ): Single<ComicDataWrapper>
}