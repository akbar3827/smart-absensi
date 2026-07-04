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

    suspend fun getFood(): Result<List<FoodModel>> {
        return try {
            val snapshot = db.collection(COLLECTION_NAME)
                .get()
                .await()

            val food = snapshot.documents.mapNotNull {
                it.toObject(FoodModel::class.java)
            }
            Result.success(food)
        } catch (e: Exception) {
            Log.e("FoodRepository", "Erro fetching canteen_food")
            Result.failure(e)
        }
    }
}