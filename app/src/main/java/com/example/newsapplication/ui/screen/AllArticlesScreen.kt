package com.example.newsapplication.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.ui.viewmodel.NewsViewModel
import com.example.newsapplication.utils.Result
import org.koin.androidx.compose.koinViewModel


@Composable
fun ShowArticlesList(
    navController: NavHostController, newsViewModel: NewsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val articlesResult by newsViewModel.articles.collectAsStateWithLifecycle()

    when (articlesResult) {
        is Result.Loading -> {
            // Show a loading indicator
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        is Result.Success -> {
            val articles = (articlesResult as Result.Success<List<Article>>).data
            HandleSuccessState(navController, newsViewModel, articles)
        }

        is Result.Error -> {
            // Show an error message
            val error = (articlesResult as Result.Error).exception
            Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun HandleSuccessState(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    articles: List<Article>
) {
    val context = LocalContext.current
    LazyColumn {
        items(articles) { article ->
            val isFavorite = remember { mutableStateOf(false) }
            ShowArticleItem(
                article = article,
                isFavorite = isFavorite,
                navController = navController,
            ) {
                if (isFavorite.value) {
                    newsViewModel.deleteArticle(article)
                    isFavorite.value = false
                    Toast.makeText(context, "Article deleted", Toast.LENGTH_SHORT).show()
                } else {
                    newsViewModel.saveArticle(article)
                    isFavorite.value = true
                    Toast.makeText(context, "Article saved", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


