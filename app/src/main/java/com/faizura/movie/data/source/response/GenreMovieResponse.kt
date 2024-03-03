package com.faizura.movie.data.source.response

import android.os.Parcelable
import com.faizura.movie.data.source.model.Genre
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreMovieResponse(

    @field:SerializedName("genres")
    val genres: List<Genre>
) : Parcelable
