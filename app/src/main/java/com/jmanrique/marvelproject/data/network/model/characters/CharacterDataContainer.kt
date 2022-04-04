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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacterDataContainer

        if (offset != other.offset) return false
        if (limit != other.limit) return false
        if (total != other.total) return false
        if (count != other.count) return false
        if (results != null) {
            if (other.results == null) return false
            if (!results.contentEquals(other.results)) return false
        } else if (other.results != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = offset
        result = 31 * result + limit
        result = 31 * result + total
        result = 31 * result + count
        result = 31 * result + (results?.contentHashCode() ?: 0)
        return result
    }
}