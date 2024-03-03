package com.faizura.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faizura.movie.data.source.model.Genre
import com.faizura.movie.databinding.ItemGenreBinding

class AdapterGenreMovies(
    private val listGenreMovies: List<Genre>,
    private val setOnClickGenre: SetOnClickGenre
) : RecyclerView.Adapter<AdapterGenreMovies.ListViewGenreMovies>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewGenreMovies {
        val itemGenreBinding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewGenreMovies(itemGenreBinding)
    }

    override fun onBindViewHolder(holder: ListViewGenreMovies, position: Int) {
        holder.bind(listGenreMovies[position])
    }

    override fun getItemCount(): Int = listGenreMovies.size

    inner class ListViewGenreMovies(private val itemGenreBinding: ItemGenreBinding) :
        RecyclerView.ViewHolder(itemGenreBinding.root) {
        fun bind(genre: Genre) {
            with(itemGenreBinding) {
                genreName.text = genre.name

                itemView.setOnClickListener {
                    genre.id?.let { it1 -> setOnClickGenre.onClickGenre(it1) }
                }
            }
        }
    }

    interface SetOnClickGenre {
        fun onClickGenre(genreId: Int)
    }
}