package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMarvelEventResponse(
    @field:SerializedName("data")
    val data: DataResponse? = null,
)
