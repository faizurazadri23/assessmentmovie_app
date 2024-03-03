package com.faizura.movie.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faizura.movie.data.source.repository.MoviesRepository
import com.faizura.movie.injection.Injection
import com.faizura.movie.ui.viewmodel.MoviesViewModel

class ViewModelFactoryMovies(private val moviesRepository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(moviesRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class" + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryMovies? = null

        fun getInstance(context: Context): ViewModelFactoryMovies = instance ?: synchronized(this) {
            instance ?: ViewModelFactoryMovies(Injection.provideMoviesRepository())
        }.also { instance = it }
    }
}