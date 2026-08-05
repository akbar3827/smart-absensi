package com.learn.smartabsensi.features.data.models

data class FoodModel(
    val id: String = "",
    val name: String = "",
    val price: Int = 0,
    val description: String = "",
    val imageUrl: String = "",
    val available: Boolean = false,
    val type: String = "",
    val productName: String = ""
)