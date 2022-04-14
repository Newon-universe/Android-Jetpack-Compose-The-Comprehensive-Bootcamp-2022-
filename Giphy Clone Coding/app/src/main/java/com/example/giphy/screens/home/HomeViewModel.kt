package com.example.giphy.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.giphy.repository.SamplePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val samplePagingFlow: Flow<PagingData<String>> = Pager(
        PagingConfig(pageSize = 10, initialLoadSize = 10)
    ) { SamplePagingSource() }.flow.cachedIn(viewModelScope)
}