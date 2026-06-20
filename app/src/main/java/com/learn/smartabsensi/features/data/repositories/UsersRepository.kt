package com.learn.smartabsensi.features.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.features.data.models.UserModel

class UsersRepository {
    companion object {
        val COLLECTION_NAME = "users"
    }
    private val db = FirebaseFirestore.getInstance()

    suspend fun getUsers(): Result<List<UserModel>> {
        return try {
            val snapshot =
        } catch (e: Exception) {

        }
    }
}