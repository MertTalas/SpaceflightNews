package com.mert.spaceflightnews.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mert.spaceflightnews.domain.model.Article
import com.mert.spaceflightnews.presentation.navigation.Screen
import com.mert.spaceflightnews.presentation.viewmodel.ArticleListViewModel

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticleListViewModel = hiltViewModel()
) {
    val articles by viewModel.articles.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex == articles.lastIndex && !isLoading) {
                    viewModel.loadArticles()
                }
            }
    }

    Scaffold(
        topBar = {
            ArticleListToolbar("Articles")
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        ArticleList(
            articles = articles,
            listState = listState,
            paddingValues = paddingValues,
            isLoading = isLoading,
            onItemClick = { article ->
                navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                navController.navigate(Screen.ArticleDetail.route)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListToolbar(screenTitle: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(
            title = { Text(screenTitle) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}

@Composable
fun ArticleList(
    articles: List<Article>,
    listState: LazyListState,
    paddingValues: PaddingValues,
    isLoading: Boolean,
    onItemClick: (Article) -> Unit
) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(articles) { index, article ->
            ArticleItem(
                article = article,
                onItemClick = { onItemClick(article) }
            )
        }

        if (isLoading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .clickable(onClick = onItemClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = article.imageUrl,
                contentScale = ContentScale.Crop,
            ),
            contentDescription = "Article Image",
            modifier = Modifier
                .size(80.dp)
                .padding(end = 8.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${article.publishedAt ?: "Publish Info Not Available"} - ${article.newsSite ?: "Site Info Not Available"}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp
            )
        }
    }
}