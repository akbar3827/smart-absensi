package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.features.presentation.components.ErrorResponse
import com.learn.smartabsensi.features.presentation.components.LoadingResponse
import com.learn.smartabsensi.features.presentation.components.profile.UserProfileUiSuccess
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel
import com.learn.smartabsensi.features.presentation.view_models.UserProfileUiState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun ProfilePage(
    navigator: DestinationsNavigator,
    pvm: ProfileViewModel
) {
    val userProfileUIState by pvm.userProfileUiState.collectAsStateWithLifecycle()

    when (val state = userProfileUIState) {
        is UserProfileUiState.IsLoading -> {
            LoadingResponse()
        }
        is UserProfileUiState.Error -> {
            ErrorResponse()
        }
        is UserProfileUiState.Success -> {
            UserProfileUiSuccess(
                navigator = navigator,
                pvm = pvm,
                user = state.data
            )
        }
    }
}