package com.example.dicoding_made_2022.di

import com.example.core.domain.usecase.MarvelEventUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavouriteModuleDependencies {
    fun marvelEventUseCase(): MarvelEventUseCase
}