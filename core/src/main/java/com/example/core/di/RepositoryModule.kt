package com.example.core.di

import com.example.core.data.MarvelEventRepository
import com.example.core.domain.repository.IMarvelEventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(marvelEventRepository: MarvelEventRepository): IMarvelEventRepository

}