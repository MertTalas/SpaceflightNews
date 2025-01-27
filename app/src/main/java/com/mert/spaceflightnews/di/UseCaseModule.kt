package com.mert.spaceflightnews.di

import com.mert.spaceflightnews.domain.repository.ArticleRepository
import com.mert.spaceflightnews.domain.usecase.GetArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetArticlesUseCase(repository: ArticleRepository): GetArticlesUseCase {
        return GetArticlesUseCase(repository)
    }
}