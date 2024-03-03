package com.faizura.movie.data.source.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.faizura.movie.BuildConfig
import com.faizura.movie.api.ApiConfig
import com.faizura.movie.data.source.model.ReviewMovie

class ReviewPagingSource(
    private val apiConfig: ApiConfig,
    private val movieId: Int,
    private val page: Int
) : PagingSource<Int, ReviewMovie>() {

    private companion object {
        const val INITIAL_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ReviewMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewMovie> {
        val position = params.key ?: INITIAL_INDEX

        return try {
            val response = apiConfig.getApiService()
                .reviewMovie("Bearer ${BuildConfig.AUTH_KEY}", movieId, "en-US", page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (position == INITIAL_INDEX) null else position - 1,
                nextKey = if (response.results.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}