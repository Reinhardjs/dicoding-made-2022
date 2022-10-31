package com.example.dicoding_made_2022.ui.activities.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.MarvelEvent
import com.example.core.domain.usecase.MarvelEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val marvelEventUseCase: MarvelEventUseCase) :
    ViewModel() {
    fun setFavoriteMarvelEvent(marvelEvent: MarvelEvent, newStatus: Boolean) =
        marvelEventUseCase.setFavouriteMarvelEvent(marvelEvent, newStatus)
}