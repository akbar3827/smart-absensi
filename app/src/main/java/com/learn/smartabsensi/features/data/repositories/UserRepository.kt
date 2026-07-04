package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.features.data.models.UserModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    companion object {
        val COLLECTION_NAME = "students"
    }

    suspend fun getUser(uid: String?): Result<UserModel> {
        return try {
            if (uid != null) {
                val snapshotRef = db
                    .collection(COLLECTION_NAME)
                    .document(uid)

                val userSnapshot = snapshotRef.get().await()
                val user = userSnapshot.toObject(
                    UserModel::class.java
                ) ?: throw Exception("User not found")

                Result.success(user)
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            Log.e("UsersRepository", "Error fetching users", e)
            Result.failure(e)
        }
    }
}