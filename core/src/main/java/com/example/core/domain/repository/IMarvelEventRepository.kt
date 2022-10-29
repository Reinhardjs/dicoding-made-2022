package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.MarvelEvent
import kotlinx.coroutines.flow.Flow

interface IMarvelEventRepository {
    fun getAllMarvelEvent(): Flow<Resource<List<MarvelEvent>>>

    fun getAllFavouriteMarvelEvent(): Flow<List<MarvelEvent>>

    fun setFavouriteMarvelEvent(marvelEvent: MarvelEvent, state: Boolean)

    fun searchMarvelEvent(value: String): Flow<List<MarvelEvent>>
}