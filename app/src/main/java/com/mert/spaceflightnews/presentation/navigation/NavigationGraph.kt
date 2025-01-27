package com.mert.spaceflightnews.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mert.spaceflightnews.domain.model.Article
import com.mert.spaceflightnews.presentation.ui.ArticleDetailScreen
import com.mert.spaceflightnews.presentation.ui.ArticleListScreen

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.ArticleList.route) {
        composable(route = Screen.ArticleList.route) {
            ArticleListScreen(navController = navController)
        }
        composable(
            route = Screen.ArticleDetail.route,
            arguments = listOf(navArgument("article") {
                type = NavType.ParcelableType(Article::class.java)
            })
        ) { backStackEntry ->
            val article = backStackEntry.arguments?.getParcelable<Article>("article")
            article?.let {
                ArticleDetailScreen(navController = navController, article = it)
            }
        }
    }
}