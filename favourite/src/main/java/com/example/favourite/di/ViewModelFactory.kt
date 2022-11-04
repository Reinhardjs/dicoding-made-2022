package com.example.favourite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.MarvelEventUseCase
import com.example.favourite.fragments.favourite.FavouriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val marvelEventUseCase: MarvelEventUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> {
                FavouriteViewModel(marvelEventUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}