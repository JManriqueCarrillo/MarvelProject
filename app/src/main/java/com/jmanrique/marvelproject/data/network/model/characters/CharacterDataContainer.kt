package com.jmanrique.marvelproject.data.network.model.characters

import com.google.gson.annotations.SerializedName

data class CharacterDataContainer(
    @SerializedName("offset")
    val offset: Int, //The requested offset (number of skipped results) of the call
    @SerializedName("limit")
    val limit: Int, //The requested result limit
    @SerializedName("total")
    val total: Int, //The total number of resources available given the current filter set
    @SerializedName("count")
    val count: Int, //The total number of results returned by this call
    @SerializedName("results")
    val results: Array<Character>? //The list of characters returned by the call
)