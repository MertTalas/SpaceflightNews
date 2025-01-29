package com.mert.spaceflightnews.presentation

import com.mert.spaceflightnews.domain.model.Article
import com.mert.spaceflightnews.domain.usecase.GetArticlesUseCase
import com.mert.spaceflightnews.presentation.viewmodel.ArticleListViewModel
import com.mert.spaceflightnews.utils.TestCoroutineDispatchers
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getArticlesUseCase: GetArticlesUseCase = mockk()
    private val dispatchers = TestCoroutineDispatchers()
    private lateinit var viewModel: ArticleListViewModel

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
        coEvery { getArticlesUseCase(any(), any()) } returns flow { emit(fakeArticles) }
        viewModel = ArticleListViewModel(getArticlesUseCase, dispatchers)
    }

    @Test
    fun `loadArticles updates articles state when successful`() = runTest {
        coEvery { getArticlesUseCase(any(), any()) } returns flow { emit(fakeArticles) }

        viewModel.loadArticles()

        val expectedArticles = listOf(
            Article(
                id = 1,
                title = "Test Article",
                url = "https://test.com",
                imageUrl = "https://test.com/image.jpg",
                newsSite = "Test News",
                summary = "Test Summary",
                publishedAt = "2024-01-29T10:00:00Z",
                updatedAt = "2024-01-29T10:00:00Z"
            ),
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

        assertEquals(expectedArticles, viewModel.articles.value)
        assertFalse(viewModel.isLoading.value)
        coVerify { getArticlesUseCase(10, 0) }
    }

    @Test
    fun `refreshArticles clears articles and reloads`() = runTest {
        coEvery { getArticlesUseCase(any(), any()) } returns flow { emit(fakeArticles) }

        viewModel.refreshArticles()

        advanceUntilIdle()

        assertTrue(viewModel.articles.value == fakeArticles)
        assertFalse(viewModel.isLoading.value)
        coVerify { getArticlesUseCase(10, 0) }
    }

    @Test
    fun `loadArticles sets loading state to false after error`() = runTest {
        coEvery { getArticlesUseCase(any(), any()) } returns flow { throw Exception() }

        viewModel.loadArticles()
        advanceUntilIdle()

        assertFalse(viewModel.isLoading.value)
        coVerify { getArticlesUseCase(10, 0) }
    }
}