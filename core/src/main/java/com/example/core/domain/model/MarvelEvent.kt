package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelEvent(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail
) : Parcelable