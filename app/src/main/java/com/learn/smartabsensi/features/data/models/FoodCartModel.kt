package com.learn.smartabsensi.features.data.models

import kotlinx.serialization.Serializable

@Serializable
data class FoodCartModel(
    val id: String = "",
    val name: String = "",
    val total: String = ""
)