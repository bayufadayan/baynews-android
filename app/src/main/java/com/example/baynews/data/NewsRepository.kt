package com.example.baynews.data

import com.example.baynews.BuildConfig
import com.example.baynews.data.remote.ApiClient

class NewsRepository {

    private val api = ApiClient.api
    private val key = BuildConfig.NEWS_API_KEY

    suspend fun getHeadlines(): List<com.example.baynews.domain.Article> {
        return api.getTopHeadlines(
            category = "business",
            pageSize = 5,
            apiKey = key
        ).articles.map { it.toDomain() }
    }

    suspend fun getEverything(page: Int, pageSize: Int): Pair<List<com.example.baynews.domain.Article>, Int> {
        val res = api.getEverything(
            query = "business",
            page = page,
            pageSize = pageSize,
            apiKey = key
        )
        return res.articles.map { it.toDomain() } to res.totalResults
    }
}
