package com.example.baynews.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.baynews.domain.Article
import com.example.baynews.ui.home.HomeViewModel
import com.example.baynews.screens.components.HeadlineCard
import com.example.baynews.screens.components.NewsRow

@Composable
fun HomeScreen(
    onOpenDetail: (String) -> Unit,
    vm: HomeViewModel
) {
    val state by vm.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) { vm.loadInitial() }

    LaunchedEffect(state.loadMoreError) {
        state.loadMoreError?.let { msg ->
            val result = snackbarHostState.showSnackbar(msg, "Retry")
            if (result == SnackbarResult.ActionPerformed) {
                vm.loadNextPage()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            when {
                state.isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                state.errorMessage != null -> {
                    ErrorScreen(msg = state.errorMessage!!, onRetry = { vm.loadInitial() })
                }
                else -> {
                    HomeContent(
                        headline = state.headline,
                        news = state.news,
                        isLoadingMore = state.isLoadingMore,
                        onLoadMore = { vm.loadNextPage() },
                        onOpenDetail = onOpenDetail
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeContent(
    headline: List<Article>,
    news: List<Article>,
    isLoadingMore: Boolean,
    onLoadMore: () -> Unit,
    onOpenDetail: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Section Headline
        item {
            Column {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Top Headlines",
                    // FIX: Menambahkan parameter fontWeight =
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(12.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(headline) { _, item ->
                        HeadlineCard(article = item, onClick = { onOpenDetail(item.id) })
                    }
                }
                Spacer(Modifier.height(24.dp))
                Text(
                    text = "Latest News",
                    // FIX: Menambahkan parameter fontWeight =
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Section News List
        itemsIndexed(news) { index, item ->
            if (index >= news.lastIndex - 1 && !isLoadingMore) {
                LaunchedEffect(Unit) { onLoadMore() }
            }
            NewsRow(article = item, onClick = { onOpenDetail(item.id) })
        }

        if (isLoadingMore) {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(msg: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Oops!", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(text = msg, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onRetry) { Text("Try Again") }
    }
}