package com.example.core.data

import android.util.Log
import com.example.core.data.source.remote.vo.ApiResponse
import kotlinx.coroutines.flow.*;

abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        try {
            if (shouldFetch(dbSource)) {
                emit(Resource.Loading())
                when (val apiResponse = createCall().first()) {
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)
                        emitAll(loadFromDB().map {
                            Resource.Success(
                                it
                            )
                        })
                    }
                    is ApiResponse.Empty -> {
                        emitAll(loadFromDB().map {
                            Resource.Success(
                                it
                            )
                        })
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        emit(
                            Resource.Error<ResultType>(
                                apiResponse.errorMessage
                            )
                        )
                    }
                }
            } else {
                emitAll(loadFromDB().map { Resource.Success(it) })
            }
        } catch (e: NoSuchElementException) {
            Log.d("NetworkBoundResource", e.message.toString())
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}