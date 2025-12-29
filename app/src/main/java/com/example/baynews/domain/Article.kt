package com.example.baynews.domain

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val sourceName: String,
    val publishedAt: String,
    val url: String
)
