package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.smartabsensi.features.data.models.ArticlesItem
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.ErrorResponse
import com.learn.smartabsensi.features.presentation.components.LoadingResponse
import com.learn.smartabsensi.features.presentation.components.home.UserHomeUiSuccess
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import com.learn.smartabsensi.features.presentation.view_models.UserHomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    hvm: HomeViewModel = viewModel(),
    onNewsPageClick: (UserModel, List<ArticlesItem?>) -> Unit,
    onNotificationPageClick: (UserModel) -> Unit,
    onNewsCLick: (ArticlesItem) -> Unit
) {
    val userHomeUiState by hvm.userHomeUiState.collectAsStateWithLifecycle()

    when (val state = userHomeUiState) {
        is UserHomeUiState.IsLoading -> {
            LoadingResponse()
        }
        is UserHomeUiState.Error -> {
            ErrorResponse()
        }
        is UserHomeUiState.Success -> {
            UserHomeUiSuccess(
                hvm = hvm,
                userData = state.data,
                onNewsPageClick = onNewsPageClick,
                onNewsCLick = onNewsCLick,
                onNotificationPageClick = onNotificationPageClick
            )
        }
    }
}