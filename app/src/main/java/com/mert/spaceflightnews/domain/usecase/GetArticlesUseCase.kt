package com.mert.spaceflightnews.domain.usecase

import com.mert.spaceflightnews.domain.model.Article
import com.mert.spaceflightnews.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase@Inject constructor(
    private val repository: ArticleRepository
) {

    suspend operator fun invoke(limit: Int, offset: Int) : Flow<List<Article>> {
        return repository.getArticles(limit, offset)
    }
}