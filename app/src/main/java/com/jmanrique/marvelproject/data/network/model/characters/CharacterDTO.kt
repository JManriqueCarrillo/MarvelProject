package com.jmanrique.marvelproject.data.network.model.characters

import com.google.gson.annotations.SerializedName
import com.jmanrique.marvelproject.data.network.model.common.Thumbnail
import com.jmanrique.marvelproject.data.network.model.common.Url
import com.jmanrique.marvelproject.domain.model.MarvelCharacter
import java.time.LocalDateTime

data class CharacterDTO(
    @SerializedName("id ")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("urls")
    val urls: List<Url>,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
) {
    fun toCharacter(): MarvelCharacter =
        MarvelCharacter(
            this.id,
            this.name,
            this.description,
            this.modified,
            this.resourceURI,
            this.urls,
            this.thumbnail
        )
}