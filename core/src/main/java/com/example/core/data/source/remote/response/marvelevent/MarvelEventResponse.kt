package com.example.core.data.source.remote.response.marvelevent

import com.google.gson.annotations.SerializedName

data class MarvelEventResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse? = null,

    @field:SerializedName("urls")
    val urls: List<URLsResponse>? = null
)
