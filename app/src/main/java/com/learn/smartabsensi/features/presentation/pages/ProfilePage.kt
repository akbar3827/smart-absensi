package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.runtime.Composable
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun ProfilePage(
    navigator: DestinationsNavigator,
    pvm: ProfileViewModel
) {

}