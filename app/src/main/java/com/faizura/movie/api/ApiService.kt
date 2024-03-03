package com.faizura.movie.api

import com.faizura.movie.data.source.ReviewMovieResponse
import com.faizura.movie.data.source.response.DetailMovieResponse
import com.faizura.movie.data.source.response.MovieResponse
import com.faizura.movie.data.source.response.VideoMovieResponse
import com.faizura.movie.data.source.response.GenreMovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    suspend fun genreMovies(
        @Header("Authorization") auth: String
    ): GenreMovieResponse

    @GET("discover/movie")
    suspend fun movies(
        @Header("Authorization") auth: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("with_genres") withGenre: Int
    ): MovieResponse

    @GET("movie/{movie_id}/videos")
    suspend fun videoMovies(
        @Header("Authorization") auth: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): VideoMovieResponse

    @GET("movie/{movie_id}")
    suspend fun detailMovie(
        @Header("Authorization") auth: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): DetailMovieResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun reviewMovie(
        @Header("Authorization") auth: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): ReviewMovieResponse
}