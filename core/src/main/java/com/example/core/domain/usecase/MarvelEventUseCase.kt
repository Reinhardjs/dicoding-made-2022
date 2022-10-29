package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.MarvelEvent
import kotlinx.coroutines.flow.Flow

interface MarvelEventUseCase {
    fun getAllMarvelEvent(): Flow<Resource<List<MarvelEvent>>>

    fun getAlLFavouriteMarvelEvent(): Flow<List<MarvelEvent>>

    fun setFavouriteMarvelEvent(marvelEvent: MarvelEvent, state: Boolean)
}