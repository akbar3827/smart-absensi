package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.features.data.models.FoodModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FoodRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    companion object {
        private val COLLECTION_NAME = "canteen_food"
    }

    suspend fun getFood(
        typeFood: String? = null,
        search: String? = null
    ): Result<List<FoodModel>> {
        return try {

            val snapshot =
                if (!search.isNullOrBlank() && !typeFood.isNullOrBlank() && !typeFood.equals("semua", ignoreCase = true)) {
                    db.collection(COLLECTION_NAME).whereEqualTo("type", typeFood.lowercase().trim())
                        .whereEqualTo("productName", search.lowercase().trim())
                        .get().await()
                } else if (!search.isNullOrBlank()) {
                    db.collection(COLLECTION_NAME)
                        .whereGreaterThanOrEqualTo("productName", search.lowercase().trim()).get()
                        .await()
                } else if (!typeFood.isNullOrBlank() && !typeFood.equals("semua", ignoreCase = true)) {
                    db.collection(COLLECTION_NAME)
                        .whereEqualTo("type", typeFood.lowercase().trim()).get()
                        .await()
                } else {
                    db.collection(COLLECTION_NAME).get().await()
                }

            val food = snapshot.documents.mapNotNull {
                it.toObject(FoodModel::class.java)
            }
            Result.success(food)
        } catch (e: Exception) {
            Log.e("FoodRepository", "Error fetching $COLLECTION_NAME: ${e.message}")
            Result.failure(e)
        }
    }
}