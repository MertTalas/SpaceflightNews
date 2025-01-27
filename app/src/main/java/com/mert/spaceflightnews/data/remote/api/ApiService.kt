package com.mert.spaceflightnews.data.remote.api

import com.mert.spaceflightnews.data.remote.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("articles/")
    suspend fun getArticles(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse
}