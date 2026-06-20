package com.learn.smartabsensi.core.utils

import com.learn.smartabsensi.R
import com.ramcosta.composedestinations.generated.destinations.CanteenPageDestination
import com.ramcosta.composedestinations.generated.destinations.HistoryPageDestination
import com.ramcosta.composedestinations.generated.destinations.HomePageDestination
import com.ramcosta.composedestinations.generated.destinations.ProfilePageDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val destination: DirectionDestinationSpec,
    val icon: Int,
    val label: String
) {
    Home(HomePageDestination, R.drawable.ic_home, "Home"),
    History(HistoryPageDestination, R.drawable.ic_history, "History"),
    Canteen(CanteenPageDestination, R.drawable.ic_canteen, "Canteen"),
    Profile(ProfilePageDestination, R.drawable.ic_user, "Profile")
}