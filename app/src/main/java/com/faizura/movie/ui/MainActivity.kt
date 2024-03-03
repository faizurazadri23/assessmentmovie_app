package com.faizura.movie.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.faizura.movie.adapter.AdapterGenreMovies
import com.faizura.movie.data.source.repository.ResultProcess
import com.faizura.movie.databinding.ActivityMainBinding
import com.faizura.movie.ui.viewmodel.MoviesViewModel
import com.faizura.movie.viewmodel.ViewModelFactoryMovies

class MainActivity : AppCompatActivity(), AdapterGenreMovies.SetOnClickGenre {

    private lateinit var mainBinding: ActivityMainBinding

    private val factoryMovies = ViewModelFactoryMovies.getInstance(this)
    private val moviesViewModel: MoviesViewModel by viewModels { factoryMovies }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.rvGenre.layoutManager = LinearLayoutManager(this)

        mainBinding.swipeRefresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                mainBinding.swipeRefresh.isRefreshing = false
                loadGenreMovies()
            }, 4000)
        }

        loadGenreMovies()
    }

    private fun loadGenreMovies() {
        moviesViewModel.genreMovies().observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultProcess.Loading -> {

                    }

                    is ResultProcess.Success -> {

                        val adapterGenreMovies = AdapterGenreMovies(result.data.genres, this)
                        adapterGenreMovies.notifyDataSetChanged()
                        mainBinding.rvGenre.adapter = adapterGenreMovies
                    }

                    is ResultProcess.Error -> {

                    }
                }
            }
        }
    }

    override fun onClickGenre(genreId: Int) {

        Intent(this, MoviesActivity::class.java).also {
            it.putExtra("data", genreId)
            startActivity(it)
        }
    }
}