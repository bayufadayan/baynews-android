package com.example.baynews.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baynews.domain.Article
import com.example.baynews.ui.home.HomeViewModel
import com.example.baynews.screens.components.HeadlineCard
import com.example.baynews.screens.components.NewsRow

@Composable
fun HomeScreen(
    onOpenDetail: (String) -> Unit,
    vm: HomeViewModel = viewModel()
) {
    val state by vm.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) { vm.loadInitial() }

    LaunchedEffect(state.loadMoreError) {
        val msg = state.loadMoreError ?: return@LaunchedEffect
        val result = snackbarHostState.showSnackbar(
            message = msg,
            actionLabel = "Retry"
        )
        if (result == SnackbarResult.ActionPerformed) {
            vm.loadNextPage()
        }
    }

    Box(Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.errorMessage != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(state.errorMessage!!)
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { vm.loadInitial() }) { Text("Retry") }
                }
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

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
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
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Top Headlines",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(10.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(headline) { _, a ->
                    HeadlineCard(article = a, onClick = { onOpenDetail(a.id) })
                }
            }

            Spacer(Modifier.height(18.dp))

            Text(
                text = "All News",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))
        }

        itemsIndexed(news) { index, a ->
            if (index == news.lastIndex) {
                LaunchedEffect(index) { onLoadMore() }
            }
            NewsRow(article = a, onClick = { onOpenDetail(a.id) })
        }

        if (isLoadingMore) {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
