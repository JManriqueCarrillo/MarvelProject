package com.jmanrique.marvelproject.data.network.model.base

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("etaf")
    val etag: String?,
    @SerializedName("data")
    val data: DataResponse?
)