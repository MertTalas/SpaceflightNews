package com.mert.spaceflightnews.di

import com.mert.spaceflightnews.data.remote.api.ApiService
import com.mert.spaceflightnews.data.repository.ArticleRepositoryImpl
import com.mert.spaceflightnews.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideExerciseRepository(apiService: ApiService): ArticleRepository {
        return ArticleRepositoryImpl(apiService)
    }
}