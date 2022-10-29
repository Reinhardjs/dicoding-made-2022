package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.MarvelEventDao
import com.example.core.data.source.local.room.MarvelEventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MarvelEventDatabase = Room.databaseBuilder(
        context,
        MarvelEventDatabase::class.java, "Movie.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMarvelEventDao(database: MarvelEventDatabase): MarvelEventDao = database.marvelEventDao()
}