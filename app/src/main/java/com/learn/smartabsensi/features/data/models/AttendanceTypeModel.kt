package com.learn.smartabsensi.features.data.models

import androidx.compose.ui.graphics.Color

data class AttendanceTypeModel(
    val attendance: String,
    val information: String,
    val requirement: String,
    val icon: Int,
    val color: Color
)