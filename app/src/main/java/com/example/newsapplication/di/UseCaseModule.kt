package com.example.newsapplication.di

import com.example.newsapplication.domain.usecase.DeleteSavedArticleUseCase
import com.example.newsapplication.domain.usecase.GetRemoteNewsUseCase
import com.example.newsapplication.domain.usecase.GetSavedArticlesUseCase
import com.example.newsapplication.domain.usecase.SaveArticleUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetSavedArticlesUseCase(get()) }
    factory { SaveArticleUseCase(get()) }
    factory { DeleteSavedArticleUseCase(get()) }
    factory { GetRemoteNewsUseCase(get()) }

}