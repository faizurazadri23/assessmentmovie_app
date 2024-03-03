package com.faizura.movie.data.source

import com.faizura.movie.data.source.model.ReviewMovie
import com.google.gson.annotations.SerializedName

data class ReviewMovieResponse(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<ReviewMovie>,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)
