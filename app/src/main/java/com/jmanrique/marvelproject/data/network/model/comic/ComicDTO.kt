package com.jmanrique.marvelproject.data.network.model.comic

import com.google.gson.annotations.SerializedName
import com.jmanrique.marvelproject.data.network.model.common.MarvelImage
import com.jmanrique.marvelproject.data.network.model.common.Url
import com.jmanrique.marvelproject.domain.model.comics.MarvelComic
import com.jmanrique.marvelproject.utils.extensions.safeValue

data class ComicDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("textObjects")
    val textObjects: List<TextObjectDTO>,
    @SerializedName("urls")
    val urls: List<Url>,
    @SerializedName("thumbnail")
    val thumbnail: MarvelImage,
    @SerializedName("images")
    val images: List<MarvelImage>?
) {
    fun toComic() = MarvelComic(
        id = this.id,
        title = this.title.safeValue(),
        description = this.description.safeValue(),
        previewText = this.textObjects.firstOrNull { it.type == "issue_preview_text" }?.text ?: "",
        urlDetail = this.urls.firstOrNull { it.type == "detail" }?.url ?: "",
        urlPurchase = this.urls.firstOrNull { it.type == "purchase" }?.url ?: "",
        thumbnail = this.thumbnail,
        images = this.images ?: emptyList()
    )

}
