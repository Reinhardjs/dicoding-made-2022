package com.example.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_marvelevent")
data class MarvelEventEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "marvelEventId")
    val marvelEventId: Int,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "thumbnail")
    val thumbnailUrl: String? = null,

    @ColumnInfo(name = "bookmarked")
    var isFavorite: Boolean = false,
) : Parcelable
