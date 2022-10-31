package com.example.favourite.fragments.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MarvelEventUseCase

class FavouriteViewModel constructor(marvelEventUseCase: MarvelEventUseCase) : ViewModel() {
    val favouriteMarvelEvent = marvelEventUseCase.getAlLFavouriteMarvelEvent().asLiveData()
}
