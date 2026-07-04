package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.features.data.models.NewsModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    companion object {
        val COLLECTION_NAME = "news"
    }

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