package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.learn.smartabsensi.features.data.models.UserAuthModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepository @Inject constructor(private val auth: FirebaseAuth) {
    suspend fun register(
        email: String,
        password: String
    ): Result<UserAuthModel> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()

            val user = auth.currentUser ?: return Result.failure(
                Exception("account creation failed")
            )

            Result.success(
                UserAuthModel(
                    uid = user.uid,
                    email = user.email.orEmpty()
                )
            )
        } catch (e: Exception) {
            Log.e("REGISTER", "${e.message}")
            Result.failure(e)
        }
    }


    suspend fun login(
        email: String,
        password: String
    ): Result<UserAuthModel> {
        return try {
            val authResult = auth
                .signInWithEmailAndPassword(email, password)
                .await()

            val user = authResult.user ?: return Result.failure(
                Exception("user not found")
            )

            Result.success(
                UserAuthModel(
                    uid = user.uid,
                    email = user.email.orEmpty()
                )
            )
        } catch (e: Exception) {
            Log.e("SIGNIN", "${e.message}")
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun reload() {
        auth.currentUser?.reload()
    }
}