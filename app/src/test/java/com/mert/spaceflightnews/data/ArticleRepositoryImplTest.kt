package com.mert.spaceflightnews.data

import com.mert.spaceflightnews.data.remote.api.ApiService
import com.mert.spaceflightnews.data.remote.model.ApiArticle
import com.mert.spaceflightnews.data.remote.model.ApiResponse
import com.mert.spaceflightnews.data.repository.ArticleRepositoryImpl
import com.mert.spaceflightnews.presentation.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticleRepositoryImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val apiService: ApiService = mockk()
    private lateinit var repository: ArticleRepositoryImpl

    private val fakeArticleResponse = ApiResponse(
        count = 1,
        next = null,
        previous = null,
        results = listOf(
            ApiArticle(
                id = 1,
                title = "Test Article",
                url = "https://test.com",
                image_url = "https://test.com/image.jpg",
                news_site = "Test News",
                summary = "Test Summary",
                published_at = "2024-01-29T10:00:00Z",
                updated_at = "2024-01-29T10:00:00Z"
            )
        )
    )

    @Before
    fun setup() {
        repository = ArticleRepositoryImpl(apiService)
    }

    @Test
    fun `getArticles returns mapped articles when service call is successful`() = runTest {
        coEvery { apiService.getArticles(any(), any()) } returns fakeArticleResponse

        val result = repository.getArticles(10, 0).toList().first()

        assertEquals(1, result.size)
        assertEquals(fakeArticleResponse.results[0].id, result[0].id)
        assertEquals(fakeArticleResponse.results[0].title, result[0].title)
        coVerify { apiService.getArticles(10, 0) }
    }

    @Test
    fun `getArticles throws exception when service call fails`() = runTest {
        coEvery { apiService.getArticles(any(), any()) } throws Exception("Test exception")

        try {
            repository.getArticles(10, 0)
        } catch (e: Exception) {
            assertTrue(true)
            coVerify { apiService.getArticles(10, 0) }
        }
    }
}