package com.learn.smartabsensi.features.data.models

import kotlinx.serialization.Serializable


@Serializable
data class UserModel(
    val uid: String = "",
    val name: String = "",
    val nickname: String = "",
    val email: String = "",
    val nisn: String = "",
    val photoUrl: String = "",
    val classRoom: String = "",
    val className: String = "",
    val gender: String = "",
    val createdAt: String = "",
    val address: String = "",
    val religion: String = "",
    val numberphone: String = "",
    val biodata: String = "",
    val favoriteFood: FoodFavModel = FoodFavModel(),
    val cartFood: FoodCartModel = FoodCartModel()
)

