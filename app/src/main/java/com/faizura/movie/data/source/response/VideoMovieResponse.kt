package com.faizura.movie.data.source.response

import com.faizura.movie.data.source.model.VideoMovies
import com.google.gson.annotations.SerializedName

data class VideoMovieResponse(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("results")
    val results: List<VideoMovies>
)
