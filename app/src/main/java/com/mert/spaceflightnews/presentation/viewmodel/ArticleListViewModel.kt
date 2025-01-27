package com.mert.spaceflightnews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mert.spaceflightnews.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
) : ViewModel() {

}