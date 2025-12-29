package com.example.baynews.ui.home

import com.example.baynews.domain.Article

data class HomeUiState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val headline: List<Article> = emptyList(),
    val news: List<Article> = emptyList(),
    val errorMessage: String? = null,
    val loadMoreError: String? = null
)
