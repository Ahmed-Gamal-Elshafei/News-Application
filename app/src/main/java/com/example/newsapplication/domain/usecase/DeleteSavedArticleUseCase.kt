package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.repository.NewsRepository

class DeleteSavedArticleUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}
