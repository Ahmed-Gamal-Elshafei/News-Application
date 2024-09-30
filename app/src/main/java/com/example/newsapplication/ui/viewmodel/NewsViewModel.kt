package com.example.newsapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.domain.usecase.DeleteSavedArticleUseCase
import com.example.newsapplication.domain.usecase.GetRemoteNewsUseCase
import com.example.newsapplication.domain.usecase.GetSavedArticlesUseCase
import com.example.newsapplication.domain.usecase.SaveArticleUseCase
import com.example.newsapplication.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getRemoteNewsUseCase: GetRemoteNewsUseCase,
    private val saveArticleUseCase: SaveArticleUseCase,
    private val deleteSavedArticleUseCase: DeleteSavedArticleUseCase,
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<Result<List<Article>>>(Result.Loading)
    val articles: StateFlow<Result<List<Article>>> = _articles.onStart {
        fetchTopHeadlines()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Result.Loading
    )

    private val _savedArticles = MutableStateFlow<List<Article>>(emptyList())
    val savedArticles: StateFlow<List<Article>> = _savedArticles.onStart {
        getSavedArticles()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            getRemoteNewsUseCase().collect {
                _articles.value = it
            }
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            saveArticleUseCase(article)
        }
    }

    private fun getSavedArticles() {
        viewModelScope.launch {
            getSavedArticlesUseCase().collect {
                _savedArticles.value = it
            }
        }

    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            deleteSavedArticleUseCase(article)
        }
    }
}