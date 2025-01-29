package com.mert.spaceflightnews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mert.spaceflightnews.domain.usecase.IntentUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val intentUrlUseCase: IntentUrlUseCase
) : ViewModel() {

    fun openArticleUrl(url: String) {
        intentUrlUseCase(url)
    }
}