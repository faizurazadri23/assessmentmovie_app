package com.faizura.movie.data.source.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorDetails(

    @field:SerializedName("avatar_path")
    val avatarPath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("rating")
    val rating: Int? = null,

    @field:SerializedName("username")
    val username: String? = null
) : Parcelable