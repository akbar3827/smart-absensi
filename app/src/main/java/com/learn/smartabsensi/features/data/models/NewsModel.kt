package com.learn.smartabsensi.features.data.models

import com.google.firebase.Timestamp

data class NewsModel(
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val category: String = "INFO",
    val createdAt: Timestamp? = null
)