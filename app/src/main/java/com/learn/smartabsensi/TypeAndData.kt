package com.learn.smartabsensi

import com.ramcosta.composedestinations.generated.destinations.CanteenDestination
import com.ramcosta.composedestinations.generated.destinations.HistoryDestination
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.ProfileDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val destination: DirectionDestinationSpec,
    val icon: Int,
    val label: String
) {
    Home(HomeScreenDestination, R.drawable.ic_home, "Home"),
    History(HistoryDestination, R.drawable.ic_history, "History"),
    Canteen(CanteenDestination, R.drawable.ic_canteen, "Canteen"),
    Profile(ProfileDestination, R.drawable.ic_user, "Profile")
}
