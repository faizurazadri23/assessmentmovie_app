package com.faizura.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faizura.movie.BuildConfig
import com.faizura.movie.data.source.model.Movies
import com.faizura.movie.databinding.ItemMoviesBinding

class AdapterMovies(
    private val listMovies: List<Movies>,
    private val setOnClickMovies: SetOnClickMovies
) : RecyclerView.Adapter<AdapterMovies.ListViewMovies>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewMovies {
        val itemMoviesBinding =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewMovies(itemMoviesBinding)
    }

    override fun onBindViewHolder(holder: ListViewMovies, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ListViewMovies(private val itemMoviesBinding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(itemMoviesBinding.root) {
        fun bind(movies: Movies) {
            with(itemMoviesBinding) {

                Glide.with(itemView).load("${BuildConfig.ASSET_URL}${movies.posterPath}")
                    .into(posterImg)

                itemView.setOnClickListener {
                    setOnClickMovies.onClickMovies(movies)
                }
            }
        }
    }

    interface SetOnClickMovies {
        fun onClickMovies(movies: Movies)
    }
}