package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MarvelEventEntity
import com.example.core.data.source.local.room.MarvelEventDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val marvelDao: MarvelEventDao) {
    fun getAllMarvelEvent(): Flow<List<MarvelEventEntity>> = marvelDao.getListMarvelEvent()

    suspend fun insertMarvelEvent(marvel: List<MarvelEventEntity>) = marvelDao.insertMarvelEvent(marvel)

    fun getFavoriteMarvelEvent(): Flow<List<MarvelEventEntity>> = marvelDao.getBookmarkedMarvelEvent()

    fun setFavoriteMarvelEvent(marvel: MarvelEventEntity, newState: Boolean) {
        marvel.isFavorite = newState
        marvelDao.updateMarvelEvent(marvel)
    }

    fun searchMarvelEvent(value: String): Flow<List<MarvelEventEntity>> = marvelDao.searchMarvelEvents(value)
}