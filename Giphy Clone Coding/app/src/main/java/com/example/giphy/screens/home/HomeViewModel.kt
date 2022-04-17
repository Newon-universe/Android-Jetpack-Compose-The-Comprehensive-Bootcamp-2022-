package com.example.giphy.screens.home

import androidx.compose.ui.graphics.Brush
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.giphy.model.room.Favorite
import com.example.giphy.model.trending.response.Data
import com.example.giphy.network.HomeAPI
import com.example.giphy.repository.SamplePagingSource
import com.example.giphy.repository.TrendingPagingSource
import com.example.giphy.repository.databaseRepository.GiphyDatabaseRepository
import com.example.giphy.repository.homeRepository.HomeRepository
import com.example.giphy.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val api: HomeAPI,
    private val databaseRepository: GiphyDatabaseRepository
) : ViewModel() {
    val samplePagingFlow: Flow<PagingData<String>> = Pager(
        PagingConfig(pageSize = 10, initialLoadSize = 10)
    ) { SamplePagingSource() }.flow.cachedIn(viewModelScope)

    fun getTabColor(index: Int) = when {
        index % 5 == 1 -> Brush.linearGradient(TabColorSecond)
        index % 5 == 2 -> Brush.linearGradient(TabColorThird)
        index % 5 == 3 -> Brush.linearGradient(TabColorFourth)
        index % 5 == 4 -> Brush.linearGradient(TabColorFifth)
        else -> Brush.linearGradient(TabColorFirst)
    }

    fun getPager(
        limit: Int, rating: String, random_id: String, bundle: String
    ): Flow<PagingData<Data>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 5),
        pagingSourceFactory = {
            TrendingPagingSource(
                api,
                limit,
                rating,
                random_id,
                bundle
            )
        }
    ).flow.cachedIn(viewModelScope)
}