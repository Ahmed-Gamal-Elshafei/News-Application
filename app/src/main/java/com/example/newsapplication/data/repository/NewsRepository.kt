package com.example.newsapplication.data.repository

import android.util.Log
import com.example.newsapplication.data.local.ArticleDao
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.model.NewsResponse
import com.example.newsapplication.data.remote.NewsApi
import com.example.newsapplication.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


class NewsRepository(
    private val newsApi: NewsApi,
    private val articleDao: ArticleDao
) {

    // Fetch top headlines from remote source
    suspend fun getTopHeadlines(pageNumber: Int = 1): Flow<Result<List<Article>>> {
        return flow {
            try {
                val response: Response<NewsResponse> = newsApi.getTopHeadlinesInEGy(
                    pageNumber = pageNumber,
                )

                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    newsResponse?.articles?.let { articles ->
                        Log.i("articles", "response from repository size: ${articles.size}")
                        emit(Result.Success(articles))
                    } ?: emit(Result.Error(Exception("No articles found")))
                } else {
                    Log.i("articles", "Error fetching articles ${response.message()}")
                    emit(Result.Error(Exception("Error fetching articles")))
                }
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }

    // Insert an article into the local database
    suspend fun saveArticle(article: Article): Long {
        return articleDao.insertArticle(article)
    }

    // Get all saved articles from the local database
    fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    // Delete an article from the local database
    suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }
}