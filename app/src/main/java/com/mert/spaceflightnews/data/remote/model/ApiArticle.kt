package com.mert.spaceflightnews.data.remote.model

import com.mert.spaceflightnews.domain.model.Article

data class ApiArticle(
    val id: Int,
    val title: String,
    val summary: String,
    val publishedAt: String?,
    val updatedAt: String?,
    val url: String,
    val imageUrl: String?,
    val newsSite: String?,
    val content: String?
) {
    fun toDomainModel(): Article {
        return Article(
            id = id,
            title = title,
            summary = summary,
            publishedAt = publishedAt,
            updatedAt = updatedAt,
            url = url,
            imageUrl = imageUrl,
            newsSite = newsSite,
            content = content
        )
    }
}