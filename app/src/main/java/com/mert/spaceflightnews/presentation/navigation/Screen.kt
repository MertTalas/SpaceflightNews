package com.mert.spaceflightnews.presentation.navigation

sealed class Screen(val route: String) {

    data object ArticleList : Screen("article_list")
    data object ArticleDetail : Screen("article_detail") {
        fun createRoute() = route
    }
}