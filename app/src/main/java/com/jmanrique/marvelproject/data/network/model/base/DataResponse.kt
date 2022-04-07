package com.jmanrique.marvelproject.data.network.model.base

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int
)