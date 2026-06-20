package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.features.data.models.NewsModel
import kotlinx.coroutines.tasks.await


class NewsRepository {
    companion object {
        val COLLECTION_NAME = "news"
    }
    private val db = FirebaseFirestore.getInstance()

    suspend fun getNews(): Result<List<NewsModel>> {
        return try {
            val snapshot = db
                .collection(COLLECTION_NAME)
                .get()
                .await()

            val news = snapshot.documents.mapNotNull {
                it.toObject(NewsModel::class.java)
            }
            Result.success(news)
        } catch (e: Exception) {
            Log.e("NewsRepository", "Error fetching news", e)
            Result.failure(e)
        }
    }
}