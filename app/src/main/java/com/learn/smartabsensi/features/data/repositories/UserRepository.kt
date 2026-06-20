package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.features.data.models.UserModel
import kotlinx.coroutines.tasks.await

class UserRepository {
    companion object {
        val COLLECTION_NAME = "users"
    }
    private val db = FirebaseFirestore.getInstance()

    suspend fun getUser(uid: String): Result<UserModel> {
        return try {
            val snapshot = db
                .collection(COLLECTION_NAME)
                .document(uid)
                .get()
                .await()

            val users = snapshot.toObject(UserModel::class.java) ?: throw Exception("User not found")
            Result.success(users)
        } catch (e: Exception) {
            Log.e("UsersRepository", "Error fetching users", e)
            Result.failure(e)
        }
    }
}