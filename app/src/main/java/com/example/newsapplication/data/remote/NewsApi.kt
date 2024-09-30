package com.example.newsapplication.data.remote

import com.example.newsapplication.data.model.NewsResponse
import com.example.newsapplication.utils.Constants.APIKEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlinesInEGy(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = APIKEY
    ): Response<NewsResponse>

}