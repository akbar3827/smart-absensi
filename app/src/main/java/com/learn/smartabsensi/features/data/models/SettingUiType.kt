package com.learn.smartabsensi.features.data.models

import androidx.compose.ui.graphics.Color
import com.learn.smartabsensi.R

data class SettingUiType(
    val icon: Int,
    val name: String,
    val color: Color,
    val description: String,
    val arrowRight: Int = R.drawable.ic_arrow_right
)
