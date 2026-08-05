package com.learn.smartabsensi.core.utils

import androidx.navigation3.runtime.NavKey
import com.learn.smartabsensi.features.data.models.ArticlesItem
import com.learn.smartabsensi.features.data.models.UserModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object Regist : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object History : Route

    @Serializable
    data object Canteen : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data class Notification(val user: UserModel) : Route

    @Serializable
    data class ChangeProfile(val user: UserModel) : Route

    @Serializable
    data class PreferenceNotification(val user: UserModel) : Route

    @Serializable
    data class ChangePassword(val user: UserModel) : Route

    @Serializable
    data class AmountOfNews(
        val news: List<ArticlesItem?>,
        val user: UserModel
    ) : Route
    @Serializable
    data class News(val news: ArticlesItem) : Route

}