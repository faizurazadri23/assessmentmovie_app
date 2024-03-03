package com.faizura.movie.data.source.response

import android.os.Parcelable
import com.faizura.movie.data.source.model.Movies
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<Movies>,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
) : Parcelable
