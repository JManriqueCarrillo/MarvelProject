package com.jmanrique.marvelproject.data.network.model.characters

import com.google.gson.annotations.SerializedName
import com.jmanrique.marvelproject.data.network.model.common.MarvelImage
import com.jmanrique.marvelproject.data.network.model.common.Url
import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacter
import com.jmanrique.marvelproject.utils.extensions.safeValue

data class CharacterDTO(
    @SerializedName("id")
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
    val thumbnail: MarvelImage,
) {
    fun toCharacter(): MarvelCharacter =
        MarvelCharacter(
            id = this.id,
            name = this.name.safeValue(),
            description = this.description.safeValue(),
            modified = this.modified.safeValue(),
            resourceURI = this.resourceURI.safeValue(),
            urlDetail = this.urls.firstOrNull { it.type == "detail" }?.url ?: "",
            thumbnail = this.thumbnail
        )
}