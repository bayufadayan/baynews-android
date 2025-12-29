package com.example.baynews.data.remote

data class ArticleDto(
    val title: String?,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String?,
    val source: SourceDto
)

data class SourceDto(
    val name: String
)
