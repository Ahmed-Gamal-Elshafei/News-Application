package com.example.newsapplication.ui.screen


import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapplication.ui.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ShowSavedArticlesList(
    navController: NavHostController, newsViewModel: NewsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val isFavorite = remember { mutableStateOf(true) }

    val savedArticles by newsViewModel.savedArticles.collectAsStateWithLifecycle()

    Log.d("ShowSavedArticlesList", "ShowSavedArticlesList: ${savedArticles.size}")

    LazyColumn {
        items(savedArticles) { article ->
            ShowArticleItem(
                article = article,
                isFavorite = isFavorite,
                navController = navController,
            ) {
                newsViewModel.deleteArticle(article)
            }
        }
    }
}