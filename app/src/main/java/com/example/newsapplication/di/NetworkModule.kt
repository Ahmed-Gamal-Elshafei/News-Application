package com.example.newsapplication.di

import com.example.newsapplication.data.remote.NewsApi
import com.example.newsapplication.utils.Constants.BASEURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideNewsApi(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {


    return Retrofit.Builder().baseUrl(BASEURL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder().addInterceptor(logging)
//        .connectTimeout(30, TimeUnit.SECONDS)
//        .readTimeout(30, TimeUnit.SECONDS)
//        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}