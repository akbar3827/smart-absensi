package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.runtime.Composable
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.view_models.ChangeProfileViewModel
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Destination<RootGraph>
@Composable
fun ChangeProfile(
    user: UserModel,
    cvm: ChangeProfileViewModel
) {

}