package com.jmanrique.marvelproject.data.network.model.common

import com.google.gson.annotations.SerializedName

data class MarvelImage(
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("path")
    val path: String?
)