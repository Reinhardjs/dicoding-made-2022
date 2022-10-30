package com.example.dicoding_made_2022.di

import com.example.core.domain.usecase.MarvelEventInteractor
import com.example.core.domain.usecase.MarvelEventUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMarvelEventUseCase(tourismInteractor: MarvelEventInteractor): MarvelEventUseCase
}