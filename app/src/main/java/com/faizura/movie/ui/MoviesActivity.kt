package com.faizura.movie.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.faizura.movie.R
import com.faizura.movie.adapter.AdapterMovies
import com.faizura.movie.data.source.model.Movies
import com.faizura.movie.data.source.repository.ResultProcess
import com.faizura.movie.databinding.ActivityMoviesBinding
import com.faizura.movie.ui.viewmodel.MoviesViewModel
import com.faizura.movie.viewmodel.ViewModelFactoryMovies

class MoviesActivity : AppCompatActivity(), AdapterMovies.SetOnClickMovies {

    private lateinit var moviesBinding: ActivityMoviesBinding
    private val factoryMovies = ViewModelFactoryMovies.getInstance(this)
    private val moviesViewModel: MoviesViewModel by viewModels { factoryMovies }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesBinding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(moviesBinding.root)

        supportActionBar?.title = getString(R.string.list_movie_by_genre)

        moviesBinding.rvMovies.layoutManager = GridLayoutManager(this, 2)

        intent.also { receivedIntent ->
            genredId = receivedIntent.getIntExtra("data", 0);

            loadMovies()
        }


    }

    private fun loadMovies() {
        moviesViewModel.movies(1, genredId).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultProcess.Loading -> {

                    }

                    is ResultProcess.Success -> {

                        val adapterMovies = AdapterMovies(result.data.results, this)
                        adapterMovies.notifyDataSetChanged()
                        moviesBinding.rvMovies.adapter = adapterMovies
                    }

                    is ResultProcess.Error -> {

                    }
                }
            }
        }
    }

    override fun onClickMovies(movies: Movies) {
        Intent(this, DetailMoviesActivity::class.java).also {
            it.putExtra("data", movies)
            startActivity(it)
        }
    }

    companion object {
        private var genredId: Int = 0
    }
}