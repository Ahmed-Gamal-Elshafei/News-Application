package com.example.newsapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.newsapplication.data.local.Converters


@Entity(tableName = "articles")
@TypeConverters(Converters::class)
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
)
