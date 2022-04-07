package com.jmanrique.marvelproject.data.network.model.characters

import com.google.gson.annotations.SerializedName

data class CharacterDataWrapper(
    @SerializedName("code")
    val code: Int, //The HTTP status code of the returned result
    @SerializedName("status")
    val status: String, //A string description of the call status
    @SerializedName("copyright")
    val copyright: String, // The copyright notice for the returned result
    @SerializedName("attributionText")
    val attributionText: String, //The attribution notice for this result. Please display either this notice or the contents of the attributionHTML field on all screens which contain data from the Marvel Comics API
    @SerializedName("attributionHTML ")
    val attributionHTML: String, // An HTML representation of the attribution notice for this result. Please display either this notice or the contents of the attributionText field on all screens which contain data from the Marvel Comics API
    @SerializedName("data")
    val data: CharacterDataContainer, //The results returned by the call
    @SerializedName("etag")
    val etag: String //A digest value of the content returned by the call
)