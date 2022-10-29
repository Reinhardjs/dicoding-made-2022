package com.example.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.data.source.local.entity.MarvelEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelEventDao {
    @Update
    fun updateMarvelEvent(marvelEvent: MarvelEventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarvelEvent(marvelEvents: List<MarvelEventEntity>)

    @Query("SELECT * FROM tb_marvelevent ")
    fun getListMarvelEvent(): Flow<List<MarvelEventEntity>>

    @Query("SELECT * FROM tb_marvelevent where bookmarked = 1")
    fun getBookmarkedMarvelEvent(): Flow<List<MarvelEventEntity>>

    @Query("SELECT * FROM tb_marvelevent WHERE marvelEventId = :marvelEventId")
    fun getDetailMarvelEventById(marvelEventId: Int): LiveData<MarvelEventEntity>

    @Query("SELECT * FROM tb_marvelevent WHERE  title LIKE '%' || :search || '%'")
    fun searchMarvelEvents(search: String): Flow<List<MarvelEventEntity>>
}