package com.faizura.movie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.faizura.movie.data.source.ReviewMovieResponse
import com.faizura.movie.data.source.model.Movies
import com.faizura.movie.data.source.repository.MoviesRepository
import com.faizura.movie.data.source.repository.ResultProcess
import com.faizura.movie.data.source.response.DetailMovieResponse
import com.faizura.movie.data.source.response.GenreMovieResponse
import com.faizura.movie.data.source.response.VideoMovieResponse

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun genreMovies(): LiveData<ResultProcess<GenreMovieResponse>> {
        return moviesRepository.genreMovies()
    }

    fun movies(genreId: Int, page: Int): LiveData<PagingData<Movies>> {
        return moviesRepository.movies(page, genreId).cachedIn(viewModelScope)
    }

    fun videoMovies(movieId: Int): LiveData<ResultProcess<VideoMovieResponse>> {
        return moviesRepository.videoMovies(movieId)
    }

    fun detailMovies(movieId: Int): LiveData<ResultProcess<DetailMovieResponse>> {
        return moviesRepository.detailMovies(movieId)
    }

    fun reviewMovie(movieId: Int, page: Int): LiveData<ResultProcess<ReviewMovieResponse>> {
        return moviesRepository.reviewMovie(movieId, page)
    }

}