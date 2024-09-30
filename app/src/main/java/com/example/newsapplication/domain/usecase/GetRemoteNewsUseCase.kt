package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.repository.NewsRepository
import com.example.newsapplication.utils.Result
import kotlinx.coroutines.flow.Flow

class GetRemoteNewsUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): Flow<Result<List<Article>>> {
        return newsRepository.getTopHeadlines()
    }
}