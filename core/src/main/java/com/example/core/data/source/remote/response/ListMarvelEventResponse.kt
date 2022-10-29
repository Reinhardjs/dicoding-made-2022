package com.example.core.data.source.remote.response

import com.example.core.data.source.remote.response.marvelevent.MarvelEventResponse
import com.google.gson.annotations.SerializedName

data class ListMarvelEventResponse(
    @field:SerializedName("results")
    val results: List<MarvelEventResponse>? = null,
)
