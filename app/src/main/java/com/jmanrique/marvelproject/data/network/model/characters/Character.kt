package com.jmanrique.marvelproject.data.network.model.characters

import com.google.gson.annotations.SerializedName
import com.jmanrique.marvelproject.data.network.model.common.Thumbnail
import com.jmanrique.marvelproject.data.network.model.common.Url
import java.util.*

data class Character (
    @SerializedName("id ")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: Date?,
    @SerializedName("resourceURI ")
    val resourceURI: String?,
    @SerializedName("urls")
    val urls: Array<Url>?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,

    )