package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MarvelEventEntity
import com.example.core.data.source.local.room.MarvelEventDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MarvelEventDao) {
    fun getAllMarvelEvent(): Flow<List<MarvelEventEntity>> = movieDao.getListMarvelEvent()

    suspend fun insertMarvelEvent(movie: List<MarvelEventEntity>) = movieDao.insertMarvelEvent(movie)

    fun getFavoriteMarvelEvent(): Flow<List<MarvelEventEntity>> = movieDao.getBookmarkedMarvelEvent()

    fun setFavoriteMarvelEvent(movie: MarvelEventEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateMarvelEvent(movie)
    }

    fun searchMarvelEvent(value: String): Flow<List<MarvelEventEntity>> = movieDao.searchMarvelEvents(value)
}