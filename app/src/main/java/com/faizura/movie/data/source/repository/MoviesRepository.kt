package com.faizura.movie.data.source.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.faizura.movie.BuildConfig
import com.faizura.movie.api.ApiConfig
import com.faizura.movie.data.source.datasource.MoviePagingSource
import com.faizura.movie.data.source.datasource.ReviewPagingSource
import com.faizura.movie.data.source.model.Movies
import com.faizura.movie.data.source.model.ReviewMovie
import com.faizura.movie.data.source.response.DetailMovieResponse
import com.faizura.movie.data.source.response.GenreMovieResponse
import com.faizura.movie.data.source.response.VideoMovieResponse

class MoviesRepository(private val apiConfig: ApiConfig) {

    fun genreMovies(
    ): LiveData<ResultProcess<GenreMovieResponse>> = liveData {
        emit(ResultProcess.Loading)

        try {
            val response = apiConfig.getApiService().genreMovies("Bearer ${BuildConfig.AUTH_KEY}")
            emit(ResultProcess.Success(response))
        } catch (e: Exception) {
            Log.e("Genere Movies : ", "Onfailure " + e.message.toString())
            emit(ResultProcess.Error(e.message.toString()))
        }
    }

    fun movies(
        genreId: Int,
        page: Int
    ): LiveData<PagingData<Movies>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiConfig, genreId, page)
            }
        ).liveData
    }

    fun videoMovies(
        movieId: Int
    ): LiveData<ResultProcess<VideoMovieResponse>> = liveData {
        emit(ResultProcess.Loading)

        try {
            val response = apiConfig.getApiService()
                .videoMovies("Bearer ${BuildConfig.AUTH_KEY}", movieId, "en-US")
            emit(ResultProcess.Success(response))
        } catch (e: Exception) {
            Log.e("Video Movies : ", "Onfailure " + e.message.toString())
            emit(ResultProcess.Error(e.message.toString()))
        }
    }

    fun detailMovies(
        movieId: Int
    ): LiveData<ResultProcess<DetailMovieResponse>> = liveData {
        emit(ResultProcess.Loading)

        try {
            val response = apiConfig.getApiService()
                .detailMovie("Bearer ${BuildConfig.AUTH_KEY}", movieId, "en-US")
            emit(ResultProcess.Success(response))
        } catch (e: Exception) {
            Log.e("Detail Movies : ", "Onfailure " + e.message.toString())
            emit(ResultProcess.Error(e.message.toString()))
        }
    }

    fun reviewMovie(
        movieId: Int,
        page: Int
    ): LiveData<PagingData<ReviewMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ReviewPagingSource(apiConfig, movieId, page)
            }
        ).liveData
    }
}