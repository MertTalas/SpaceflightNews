package com.mert.spaceflightnews.presentation.navigation

import com.mert.spaceflightnews.domain.model.Article

sealed class Screen(val route: String) {

    data object ArticleList : Screen("article_list")
    data object ArticleDetail : Screen("article_detail") {
        fun createRoute(article: Article) = "article_detail/$article"
    }
}