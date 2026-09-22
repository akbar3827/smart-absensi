package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.core.networkings.TabelName
import com.learn.smartabsensi.features.data.models.UserModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    suspend fun getUserData(uid: String?): Result<UserModel> {
        return try {
            if (uid != null) {
                val snapshotRef = db
                    .collection(TabelName._USER_)
                    .document(uid)

                val userSnapshot = snapshotRef.get().await()
                val user = userSnapshot.toObject(
                    UserModel::class.java
                ) ?: throw Exception("User not found (1)")

                Result.success(user)
            } else {
                Result.failure(Exception("User not found (2)"))
            }
        } catch (e: Exception) {
            Log.e("UsersRepository", "Error fetching users", e)
            Result.failure(e)
        }
    }

    suspend fun updateUserData(
        uid: String?,
        data: Map<String, Any>
    ): Result<Unit> {
        return try {
            if (uid != null) {
                db.collection(TabelName._USER_)
                    .document(uid)
                    .update(data)
                    .await()
                Result.success(Unit)

            } else {
                Result.failure(Exception("UID is null"))
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error updating user data", e)
            Result.failure(e)
        }
    }
}