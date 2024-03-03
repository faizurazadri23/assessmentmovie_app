package com.faizura.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faizura.movie.BuildConfig
import com.faizura.movie.data.source.model.Movies
import com.faizura.movie.databinding.ItemMoviesBinding

class AdapterMovies(
    private val setOnClickMovies: SetOnClickMovies
) : PagingDataAdapter<Movies, AdapterMovies.ListViewMovies>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewMovies {
        val itemMoviesBinding =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewMovies(itemMoviesBinding)
    }

    override fun onBindViewHolder(holder: ListViewMovies, position: Int) {
        val data = getItem(position)
        if (data!=null){
            holder.bind(data)
        }
    }

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

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movies>() {
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}