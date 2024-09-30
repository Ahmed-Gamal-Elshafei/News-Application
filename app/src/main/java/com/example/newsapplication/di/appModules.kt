package com.example.newsapplication.di


import org.koin.core.module.Module

val appModule: List<Module> = listOf(
    networkModule,
    databaseModule,
    repositoryModule,
    viewModelModule,
    useCaseModule
    // other modules (e.g., repository, use case, etc.)
)