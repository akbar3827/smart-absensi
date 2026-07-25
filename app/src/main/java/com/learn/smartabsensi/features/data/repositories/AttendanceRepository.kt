package com.learn.smartabsensi.features.data.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.learn.smartabsensi.features.data.models.AttendanceModel
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class AttendanceRepository @Inject constructor(
    val db: FirebaseFirestore
) {
    companion object {
        val COLLECTION_NAME = "attendance"
    }

    suspend fun getAttendances(uid: String, period: String): Result<List<AttendanceModel>> {
        return try {
            val snapshot = db
                .collection(COLLECTION_NAME)
                .whereEqualTo("uid", uid)
                .whereEqualTo("period", period)
                .get()
                .await()

            val result = snapshot.documents.mapNotNull {
                it.toObject(AttendanceModel::class.java)
            }

            Result.success(result)
        } catch (e: Exception) {
            Log.e("ATTENDANCE", "error fetching attendance", e)
            Result.failure(e)
        }
    }


    suspend fun setAttendance(
        uid: String,
        name: String,
        status: String,
        classRoom: String,
        period: String,
        date: String,
        createdAt: String,
        throwable: (String) -> Unit
    ) {
        if (
            LocalTime.now() >= LocalTime.of(5, 0) &&
            LocalTime.now() <= LocalTime.of(7, 0)
        ) {
            val today = LocalDate.now()
            val dateKey = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val docId = "${uid}_$dateKey"

            val attendanceRef = db
                .collection(COLLECTION_NAME)
                .document(docId)


            db.runTransaction { transaction ->
                val snapshot = transaction.get(attendanceRef)

                if (snapshot.exists()) {
                    throwable("Kamu sudah melakukan absensi")
                } else {
                    transaction.set(
                        attendanceRef, AttendanceModel(
                            uid,
                            name,
                            status,
                            classRoom,
                            period,
                            date,
                            createdAt
                        )
                    )
                }
            }.await()
        } else {
            throwable("Hanya bisa melakukan absensi dari pukul 5.00 - 7.00")
        }
    }
}