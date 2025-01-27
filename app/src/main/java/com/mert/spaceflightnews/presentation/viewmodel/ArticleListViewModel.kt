package com.mert.spaceflightnews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mert.spaceflightnews.domain.model.Article
import com.mert.spaceflightnews.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> get() = _articles

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private var currentOffset = 0
    private val limit = 10

    init {
        loadArticles()
    }

    fun loadArticles() {
        if (_isLoading.value) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                getArticlesUseCase(limit, currentOffset).collect { newArticles ->
                    _articles.value += newArticles
                    currentOffset += limit
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}