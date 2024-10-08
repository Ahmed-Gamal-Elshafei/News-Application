package com.example.newsapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapplication.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertArticle(article: Article): Long

        @Query("SELECT * FROM articles")
        fun getAllArticles(): Flow<List<Article>>

        @Delete
        suspend fun deleteArticle(article: Article)

}