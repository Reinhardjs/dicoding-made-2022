package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.MarvelEvent
import com.example.core.domain.repository.IMarvelEventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelEventInteractor @Inject constructor(private val marvelEventRepository: IMarvelEventRepository) :
    MarvelEventUseCase {

    override fun getAllMarvelEvent(): Flow<Resource<List<MarvelEvent>>> =
        marvelEventRepository.getAllMarvelEvent()

    override fun getAlLFavouriteMarvelEvent(): Flow<List<MarvelEvent>> =
        marvelEventRepository.getAllFavouriteMarvelEvent()

    override fun searchMarvelEvent(value: String): Flow<List<MarvelEvent>> = marvelEventRepository.searchMarvelEvent(value)

    override fun setFavouriteMarvelEvent(marvelEvent: MarvelEvent, state: Boolean) =
        marvelEventRepository.setFavouriteMarvelEvent(marvelEvent, state)

}