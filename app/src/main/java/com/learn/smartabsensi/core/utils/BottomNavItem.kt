package com.learn.smartabsensi.core.utils

import com.learn.smartabsensi.R

enum class BottomNavItem(
    val route: Route,
    val label: String,
    val icon: Int
) {
    HOME(Route.Home, "Home", R.drawable.ic_home),
    HISTORY(Route.History, "History", R.drawable.ic_history),
    CANTEEN(Route.Canteen, "Canteen", R.drawable.ic_canteen),
    PROFILE(Route.Profile, "Profile", R.drawable.ic_user)
}