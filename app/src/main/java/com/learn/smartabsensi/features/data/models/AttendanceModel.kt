package com.learn.smartabsensi.features.data.models

import com.google.firebase.Timestamp
import com.google.firestore.v1.DocumentTransform


data class AttendanceModel(
    val uid: String = "",
    val name: String = "",
    val status: String = "",
    val classRoom: String = "",
    val period: String = "",
    val date: String = "",
    val createdAt: String = ""
)