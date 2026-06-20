package com.learn.smartabsensi.features.data.models

data class UserModel(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val photoProfile: String = "",
    val school: String = "",
    val hadir: String = "0",
    val sakit: String = "0",
    val izin: String = "0",
    val dispen: String = "0",
    val alfa: String = "0",
    val createdAt: String = "",
    val attendence: AttendenceModel = AttendenceModel()
)

data class AttendenceModel(
    val status: String = "",
    val checkIn: String = ""
)
