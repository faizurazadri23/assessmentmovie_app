package com.faizura.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faizura.movie.data.source.model.Movies
import com.faizura.movie.data.source.model.ReviewMovie
import com.faizura.movie.databinding.ItemReviewMovieBinding
import java.text.SimpleDateFormat

class AdapterReviewMovie(

    private val movies: Movies,
) : PagingDataAdapter<ReviewMovie, AdapterReviewMovie.ListViewReviewMovies>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewReviewMovies {
        val itemReviewMovieBinding =
            ItemReviewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewReviewMovies(itemReviewMovieBinding)
    }

    override fun onBindViewHolder(holder: ListViewReviewMovies, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data, movies)
        }
    }


    inner class ListViewReviewMovies(private val itemReviewMovieBinding: ItemReviewMovieBinding) :
        RecyclerView.ViewHolder(itemReviewMovieBinding.root) {
        fun bind(reviewMovie: ReviewMovie, movies: Movies) {
            with(itemReviewMovieBinding) {

                titleMovie.text = movies.title
                val dateTimeFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                val dateTime = dateTimeFormatter.parse(reviewMovie.createdAt)

                val outputDateFormat = SimpleDateFormat("dd MMMM yyyy")
                val formattedDate = outputDateFormat.format(dateTime)
                descriptionReview.text = "Written by ${reviewMovie.author} on ${formattedDate}"
                review.text = reviewMovie.content

            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReviewMovie>() {
            override fun areItemsTheSame(oldItem: ReviewMovie, newItem: ReviewMovie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ReviewMovie, newItem: ReviewMovie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}