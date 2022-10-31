package com.example.dicoding_made_2022.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MarvelEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(marvelEventUseCase: MarvelEventUseCase) : ViewModel() {
    val marvelEvent = marvelEventUseCase.getAllMarvelEvent().asLiveData()
}