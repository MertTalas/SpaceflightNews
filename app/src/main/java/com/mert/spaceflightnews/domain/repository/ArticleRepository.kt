package com.mert.spaceflightnews.domain.repository

import com.mert.spaceflightnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun getArticles(limit: Int, offset: Int): Flow<List<Article>>
}