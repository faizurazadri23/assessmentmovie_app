package com.faizura.movie.data.source.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.faizura.movie.BuildConfig
import com.faizura.movie.api.ApiConfig
import com.faizura.movie.data.source.ReviewMovieResponse
import com.faizura.movie.data.source.response.DetailMovieResponse
import com.faizura.movie.data.source.response.MovieResponse
import com.faizura.movie.data.source.response.VideoMovieResponse
import com.faizura.movie.data.source.response.GenreMovieResponse

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

    fun movies(genreId: Int,
        page: Int
    ): LiveData<ResultProcess<MovieResponse>> = liveData {
        emit(ResultProcess.Loading)

        try {
            val response = apiConfig.getApiService()
                .movies("Bearer ${BuildConfig.AUTH_KEY}", false, true, "en-US", page, genreId)
            emit(ResultProcess.Success(response))
        } catch (e: Exception) {
            Log.e("Movies : ", "Onfailure " + e.message.toString())
            emit(ResultProcess.Error(e.message.toString()))
        }
    }

    fun videoMovies(movieId: Int
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

    fun detailMovies(movieId: Int
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

    fun reviewMovie(movieId: Int,
               page: Int
    ): LiveData<ResultProcess<ReviewMovieResponse>> = liveData {
        emit(ResultProcess.Loading)

        try {
            val response = apiConfig.getApiService()
                .reviewMovie("Bearer ${BuildConfig.AUTH_KEY}", movieId,"en-US", page)
            emit(ResultProcess.Success(response))
        } catch (e: Exception) {
            Log.e("Movies : ", "Onfailure " + e.message.toString())
            emit(ResultProcess.Error(e.message.toString()))
        }
    }
}