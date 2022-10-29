package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.response.marvelevent.MarvelEventResponse
import com.example.core.data.source.remote.vo.ApiResponse
import com.example.core.domain.model.MarvelEvent
import com.example.core.domain.repository.IMarvelEventRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelEventRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMarvelEventRepository {
    override fun getAllMarvelEvent(): Flow<Resource<List<MarvelEvent>>> {
        return object :
            NetworkBoundResource<List<MarvelEvent>, List<MarvelEventResponse>>() {
            override fun loadFromDB(): Flow<List<MarvelEvent>> {
                return localDataSource.getAllMarvelEvent().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MarvelEvent>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MarvelEventResponse>>> =
                remoteDataSource.getAllMarvelEvent()

            override suspend fun saveCallResult(data: List<MarvelEventResponse>) {
                val marvelEventList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMarvelEvent(marvelEventList)
            }
        }.asFlow()
    }

    override fun getAllFavouriteMarvelEvent(): Flow<List<MarvelEvent>> {
        return localDataSource.getFavoriteMarvelEvent().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavouriteMarvelEvent(marvelEvent: MarvelEvent, state: Boolean) {
        val marvelEventEntity = DataMapper.mapDomainToEntity(marvelEvent)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMarvelEvent(marvelEventEntity, state) }
    }

    override fun searchMarvelEvent(value: String): Flow<List<MarvelEvent>> {
        return localDataSource.searchMarvelEvent(value).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }
}