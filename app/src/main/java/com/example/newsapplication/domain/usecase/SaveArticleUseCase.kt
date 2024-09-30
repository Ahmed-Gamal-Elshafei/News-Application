package com.example.newsapplication.domain.usecase

import android.util.Log
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.repository.NewsRepository

class SaveArticleUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        val savedArticle = newsRepository.saveArticle(article)
        Log.d("SaveArticleUseCase", "Saved article: $savedArticle")
    }
}