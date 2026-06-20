package com.learn.smartabsensi.core.networkings

import com.learn.smartabsensi.features.data.models.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getCurrentData(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ) : ArticleResponse
}