package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.ErrorResponse
import com.learn.smartabsensi.features.presentation.components.LoadingResponse
import com.learn.smartabsensi.features.presentation.components.profile.UserProfileUiSuccess
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel
import com.learn.smartabsensi.features.presentation.view_models.UserProfileUiState

@Composable
fun ProfilePage(
    pvm: ProfileViewModel = viewModel(),
    onEditProfilePageClick: (UserModel) -> Unit,
    onLoginPageClick: () -> Unit,
    onNotificationPagePageClick: (UserModel) -> Unit,
    onChangePasswordPagePageClick: (UserModel) -> Unit
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
                pvm = pvm,
                user = state.data,
                onLoginPageClick = onLoginPageClick,
                onEditProfilePageClick = onEditProfilePageClick,
                onNotificationPagePageClick = onNotificationPagePageClick,
                onChangePasswordPagePageClick = onChangePasswordPagePageClick
            )
        }
    }
}