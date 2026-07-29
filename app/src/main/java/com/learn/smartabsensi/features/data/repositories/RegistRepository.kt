package com.learn.smartabsensi.features.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.features.data.models.UserModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegistRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    companion object {
        val COLLECTION_NAME = "students"
    }

    suspend fun setUser(
        name: String,
        nickname: String,
        uid: String,
        email: String,
        nisn: String,
        photoUrl: String,
        classRoom: String,
        major: String,
        gender: String,
        createdAt: String
    ) {
        db.collection(COLLECTION_NAME)
            .document(uid)
            .set(
                UserModel(
                    uid = uid,
                    name = name,
                    nickname = nickname,
                    email = email,
                    nisn = nisn,
                    photoUrl = photoUrl,
                    classRoom = classRoom,
                    className = major,
                    gender = gender,
                    createdAt = createdAt
                )
            ).await()
    }
}