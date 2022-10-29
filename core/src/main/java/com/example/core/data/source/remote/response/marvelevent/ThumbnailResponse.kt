package com.example.core.data.source.remote.response.marvelevent

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @field:SerializedName("path")
    val path: String? = null,

    @field:SerializedName("extension")
    val extension: String? = null
)
