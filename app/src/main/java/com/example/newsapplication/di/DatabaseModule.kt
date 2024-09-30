package com.example.newsapplication.di



import android.app.Application
import androidx.room.Room
import com.example.newsapplication.data.local.AppDatabase
import com.example.newsapplication.data.local.ArticleDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(get()) }
    single { provideArticleDao(get()) }
}

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
}

fun provideArticleDao(database: AppDatabase): ArticleDao {
    return database.articleDao()
}
