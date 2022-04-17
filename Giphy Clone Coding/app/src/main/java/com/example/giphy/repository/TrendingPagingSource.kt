package com.example.giphy.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphy.model.trending.response.Data
import com.example.giphy.network.HomeAPI
import com.example.giphy.util.Constants
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class TrendingPagingSource @Inject constructor(
    private val api: HomeAPI,
    private val limit: Int,
    private val rating: String,
    private val random_id: String,
    private val bundle: String
) :
    PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        val page = params.key ?: 0
        val response =
            api.getTrendingGif(Constants.API_KEY, limit, page, rating, random_id, bundle)

        val next = if (response.data.size < limit) null else page + 1
        val prev = if (page == 0) null else page - 1

        return try {
            if (response.meta.status == 200) {
                LoadResult.Page(
                    data = response.data,
                    prevKey = prev,
                    nextKey = next
                )
            } else {
                Log.d("TrendingPager", "load fail: ${Exception().message}")
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            if (e is CancellationException)
                throw e

            Log.d("TrendingPager", "load fail: ${Exception().message}")
            LoadResult.Error(e)
        }
    }

}