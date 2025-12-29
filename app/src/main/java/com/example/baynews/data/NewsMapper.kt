package com.example.baynews.data

import com.example.baynews.data.remote.ArticleDto
import com.example.baynews.domain.Article

fun ArticleDto.toDomain(): Article {
    return Article(
        id = url,
        title = title ?: "(No title)",
        description = description ?: "",
        imageUrl = urlToImage,
        sourceName = source.name,
        publishedAt = publishedAt ?: "",
        url = url
    )
}
