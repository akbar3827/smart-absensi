package com.learn.smartabsensi.features.data.models

import kotlinx.serialization.Serializable

@Serializable
data class FoodFavModel(
    val id: String = "",
    val name: String = ""
)