package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.MarvelEventEntity

@Database(entities = [MarvelEventEntity::class], version = 1, exportSchema = false)
abstract class MarvelEventDatabase : RoomDatabase() {

    abstract fun marvelEventDao(): MarvelEventDao

}