package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.core.networkings.TabelName
import com.learn.smartabsensi.features.data.models.NewsModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    suspend fun getNews(): Result<List<NewsModel>> {
        return try {
            val snapshot = db
                .collection(TabelName._NEWS_)
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