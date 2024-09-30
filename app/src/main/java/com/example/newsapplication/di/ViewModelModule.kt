package com.example.newsapplication.di

import com.example.newsapplication.ui.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsViewModel(get(), get(), get(), get()) }
}