package com.example.core.data.source.remote.response.marvelevent

import com.google.gson.annotations.SerializedName

data class URLsResponse (
    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)
