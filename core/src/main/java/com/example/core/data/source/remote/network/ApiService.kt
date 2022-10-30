package com.example.core.data.source.remote.network

import com.example.core.BuildConfig.*
import com.example.core.data.source.remote.response.ListMarvelEventResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    suspend fun getMarvelEvents(
        @Query("apiKey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): ListMarvelEventResponse
}