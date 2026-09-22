package com.learn.smartabsensi.features.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.core.networkings.TabelName
import com.learn.smartabsensi.features.data.models.UserModel
import kotlinx.coroutines.tasks.await

class UsersRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getUsers(): Result<List<UserModel>> {
        return try {
            val snapshot = db
                .collection(TabelName._USER_)
                .get()
                .await()

            val users = snapshot.documents.mapNotNull {
                it.toObject(UserModel::class.java)
            }
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}