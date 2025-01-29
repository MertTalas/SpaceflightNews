package com.mert.spaceflightnews.data.remote.model

import com.mert.spaceflightnews.domain.model.Article

data class ApiArticle(
    val id: Int,
    val title: String,
    val url: String,
    val image_url: String?,
    val news_site: String?,
    val summary: String,
    val published_at: String?,
    val updated_at: String?
) {
    fun toDomainModel(): Article {
        return Article(
            id = id,
            title = title,
            url = url,
            imageUrl = image_url,
            newsSite = news_site,
            summary = summary,
            publishedAt = published_at,
            updatedAt = updated_at
        )
    }
}