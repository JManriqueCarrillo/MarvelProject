package com.jmanrique.marvelproject.data.network.model.comic

import com.google.gson.annotations.SerializedName
import com.jmanrique.marvelproject.domain.model.comics.MarvelComicContainer

data class ComicDataContainer(
    @SerializedName("offset")
    val offset: Int, //The requested offset (number of skipped results) of the call
    @SerializedName("limit")
    val limit: Int, //The requested result limit
    @SerializedName("total")
    val total: Int, //The total number of resources available given the current filter set
    @SerializedName("count")
    val count: Int, //The total number of results returned by this call
    @SerializedName("results")
    val results: List<ComicDTO> //The list of comics returned by the call
) {

    fun toMarvelComicContainer() =
        this.results.map {
            it.toComic()
        }.let {
            MarvelComicContainer(
                this.total,
                it
            )
        }
}