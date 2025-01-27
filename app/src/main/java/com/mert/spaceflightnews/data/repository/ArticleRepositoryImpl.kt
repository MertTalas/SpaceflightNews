package com.mert.spaceflightnews.data.repository

import com.mert.spaceflightnews.data.remote.api.ApiService
import com.mert.spaceflightnews.domain.model.Article
import com.mert.spaceflightnews.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleRepositoryImpl(
    private val apiService: ApiService
) : ArticleRepository {

    override suspend fun getArticles(limit: Int, offset: Int): Flow<List<Article>> = flow {
        try {
            val response = apiService.getArticles(limit, offset)
            val exercises = response.map { it.toDomainModel() }
            emit(exercises)
        } catch (e: Exception) {
            emit(emptyList())
            e.printStackTrace()
        }
    }
}