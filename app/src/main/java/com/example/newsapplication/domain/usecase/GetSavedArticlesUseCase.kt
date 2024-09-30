package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedArticlesUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getSavedArticles()
    }

}