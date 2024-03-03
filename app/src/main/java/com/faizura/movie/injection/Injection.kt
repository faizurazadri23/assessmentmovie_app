package com.faizura.movie.injection

import com.faizura.movie.api.ApiConfig
import com.faizura.movie.data.source.repository.MoviesRepository

object Injection {

    fun provideMoviesRepository(): MoviesRepository {
        val apiConfig = ApiConfig
        return MoviesRepository(apiConfig)
    }
}