package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelEvent(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val thumbnail: Thumbnail? = null,
    val urls: List<URLs>? = null,
    val isFavorite: Boolean
) : Parcelable