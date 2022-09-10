package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val movieId : Int,
    val tittle : String,
    val description : String,
    val image : String? = null,
    val isFavorite : Boolean
) : Parcelable