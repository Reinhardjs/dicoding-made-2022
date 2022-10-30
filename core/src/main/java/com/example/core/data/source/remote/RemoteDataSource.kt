package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.marvelevent.MarvelEventResponse
import com.example.core.data.source.remote.vo.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllMarvelEvent(): Flow<ApiResponse<List<MarvelEventResponse>>> {
        return flow {
            try {
                val response = apiService.getMarvelEvents()
                val dataArray = response.results

                if (dataArray != null) {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(response.results))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}