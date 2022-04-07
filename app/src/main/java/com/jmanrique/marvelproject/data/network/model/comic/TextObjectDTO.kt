package com.jmanrique.marvelproject.data.network.model.comic

import com.google.gson.annotations.SerializedName
import java.util.*

data class TextObjectDTO(
    @SerializedName("type")
    val type: String,
    @SerializedName("language")
    val language: Locale,
    @SerializedName("text")
    val text: String
){}