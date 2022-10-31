package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class URLs(
    val type: String? = null,
    val url: String? = null
): Parcelable
