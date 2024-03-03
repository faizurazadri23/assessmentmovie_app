package com.faizura.movie.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.faizura.movie.BuildConfig
import com.faizura.movie.adapter.AdapterReviewMovie
import com.faizura.movie.data.source.model.Movies
import com.faizura.movie.data.source.repository.ResultProcess
import com.faizura.movie.databinding.ActivityDetailMoviesBinding
import com.faizura.movie.ui.viewmodel.MoviesViewModel
import com.faizura.movie.viewmodel.ViewModelFactoryMovies

class DetailMoviesActivity : AppCompatActivity() {

    private lateinit var detailMoviesBinding: ActivityDetailMoviesBinding
    private val factoryMovies = ViewModelFactoryMovies.getInstance(this)
    private val moviesViewModel: MoviesViewModel by viewModels { factoryMovies }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMoviesBinding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(detailMoviesBinding.root)

        detailMoviesBinding.rvReviewMovie.layoutManager = LinearLayoutManager(this)

        intent.also { receivedIntent ->
            movies = receivedIntent.getParcelableExtra("data")!!

            supportActionBar?.hide()
            detailMoviesBinding.titleMovie.text = movies.title
            detailMoviesBinding.descriptionMovie.text = movies.overview
            movies.id?.let {
                loadVideoMovie(it)
                detailMovie(it)
                loadReviewMovie(it)
            }
        }

        val webViewSetting = detailMoviesBinding.trailerVideo.settings
        webViewSetting.javaScriptEnabled = true


    }

    private fun loadVideoMovie(movieId: Int) {
        moviesViewModel.videoMovies(movieId).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultProcess.Loading -> {

                    }

                    is ResultProcess.Success -> {

                        detailMoviesBinding.trailerVideo.loadUrl("https://www.youtube.com/embed/${result.data.results[1].key}")
                    }

                    is ResultProcess.Error -> {

                    }
                }
            }
        }
    }

    private fun detailMovie(movieId: Int) {
        moviesViewModel.detailMovies(movieId).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultProcess.Loading -> {

                    }

                    is ResultProcess.Success -> {

                        detailMoviesBinding.titleMovie.text = result.data.title
                        detailMoviesBinding.descriptionMovie.text = result.data.overview
                        Glide.with(this).load("${BuildConfig.ASSET_URL}${movies.posterPath}")
                            .into(detailMoviesBinding.posterImg)
                        detailMoviesBinding.duration.text = "${result.data.runtime} menit"

                        val genreStr = StringBuilder()
                        for (genre in result.data.genres) {
                            genreStr.append(genre.name).toString()
                        }
                        val isAdult = if (result.data.adult == true) {
                            "Ya"
                        } else {
                            "Tidak"
                        }

                        detailMoviesBinding.isAdult.text = isAdult
                        detailMoviesBinding.genre.text = genreStr
                        detailMoviesBinding.popularity.text = result.data.popularity.toString()
                    }

                    is ResultProcess.Error -> {

                    }
                }
            }
        }
    }

    private fun loadReviewMovie(movieId: Int) {
        moviesViewModel.reviewMovie(movieId, 1).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultProcess.Loading -> {

                    }

                    is ResultProcess.Success -> {
                        val adapterReview = AdapterReviewMovie(result.data.results, movies)
                        adapterReview.notifyDataSetChanged()
                        detailMoviesBinding.rvReviewMovie.adapter = adapterReview
                    }

                    is ResultProcess.Error -> {

                    }
                }
            }
        }
    }

    companion object {
        lateinit var movies: Movies
    }
}