package com.example.newsapplication.di

import com.example.newsapplication.data.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsRepository(get(), get()) }
}