package com.mert.spaceflightnews.domain

import com.mert.spaceflightnews.domain.model.Article
import com.mert.spaceflightnews.domain.repository.ArticleRepository
import com.mert.spaceflightnews.domain.usecase.GetArticlesUseCase
import com.mert.spaceflightnews.presentation.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetArticlesUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: ArticleRepository = mockk()
    private lateinit var useCase: GetArticlesUseCase

    private val fakeArticles = listOf(
        Article(
            id = 1,
            title = "Test Article",
            url = "https://test.com",
            imageUrl = "https://test.com/image.jpg",
            newsSite = "Test News",
            summary = "Test Summary",
            publishedAt = "2024-01-29T10:00:00Z",
            updatedAt = "2024-01-29T10:00:00Z"
        )
    )

    @Before
    fun setup() {
        useCase = GetArticlesUseCase(repository)
    }

    @Test
    fun `invoke returns articles from repository`() = runTest {
        coEvery { repository.getArticles(any(), any()) } returns flow { emit(fakeArticles) }

        val result = useCase(10, 0).first()

        assertEquals(fakeArticles, result)
        coVerify { repository.getArticles(10, 0) }
    }

    @Test
    fun `invoke propagates repository error`() = runTest {
        coEvery { repository.getArticles(any(), any()) } returns flow { throw Exception() }

        try {
            runBlocking { useCase(10, 0).first() }
            fail("Expected an Exception to be thrown")
        } catch (e: Exception) {
            assertTrue(true)
        }
    }
}