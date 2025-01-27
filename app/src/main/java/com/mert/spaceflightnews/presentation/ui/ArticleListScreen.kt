package com.mert.spaceflightnews.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mert.spaceflightnews.presentation.viewmodel.ArticleListViewModel

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticleListViewModel = hiltViewModel()
) {

}