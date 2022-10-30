package com.example.dicoding_made_2022.di

import com.example.core.domain.usecase.MarvelEventInteractor
import com.example.core.domain.usecase.MarvelEventUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMarvelEventUseCase(marvelEventInteractor: MarvelEventInteractor): MarvelEventUseCase

}