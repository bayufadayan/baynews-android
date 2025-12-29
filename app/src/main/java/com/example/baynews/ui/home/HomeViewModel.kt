package com.example.baynews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baynews.data.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: NewsRepository = NewsRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    private var page = 1
    private val articleMap = linkedMapOf<String, com.example.baynews.domain.Article>()
    fun findById(id: String): com.example.baynews.domain.Article? = articleMap[id]
    private val pageSize = 10
    private var totalResults = Int.MAX_VALUE

    fun loadInitial() {
        page = 1
        totalResults = Int.MAX_VALUE

        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)

            try {
                val headlines = repo.getHeadlines()
                val (list, total) = repo.getEverything(page = page, pageSize = pageSize)
                totalResults = total
                page++
                articleMap.clear()
                (headlines + list).forEach { articleMap[it.id] = it }

                _uiState.value = HomeUiState(
                    isLoading = false,
                    headline = headlines,
                    news = list
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    isLoading = false,
                    errorMessage = e.message ?: "Gagal load berita"
                )
            }
        }
    }

    fun loadNextPage() {
        val s = _uiState.value
        if (s.isLoadingMore || s.isLoading) return
        if (s.news.size >= totalResults) return

        viewModelScope.launch {
            _uiState.value = s.copy(isLoadingMore = true, loadMoreError = null)

            try {
                val (list, total) = repo.getEverything(page = page, pageSize = pageSize)
                totalResults = total
                page++
                list.forEach { articleMap[it.id] = it }

                _uiState.value = _uiState.value.copy(
                    isLoadingMore = false,
                    news = _uiState.value.news + list,
                    loadMoreError = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoadingMore = false,
                    loadMoreError = e.message ?: "Gagal load halaman berikutnya"
                )
            }
        }
    }
}
