package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.ErrorResponse
import com.learn.smartabsensi.features.presentation.components.LoadingResponse
import com.learn.smartabsensi.features.presentation.components.history.UserHistoryUiSuccess
import com.learn.smartabsensi.features.presentation.view_models.HistoryViewModel
import com.learn.smartabsensi.features.presentation.view_models.UserHistoryUiState

@Composable
fun HistoryPage(
    hvm: HistoryViewModel = viewModel(),
    onNotificationClick: (UserModel) -> Unit
) {
    val user by hvm.user.collectAsStateWithLifecycle()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        when (val state = user)  {
            is UserHistoryUiState.isLoading -> {
                LoadingResponse()
            }
            is UserHistoryUiState.Error -> {
                ErrorResponse()
            }
            is UserHistoryUiState.Success -> {
                UserHistoryUiSuccess(
                    hvm = hvm,
                    user = state.user,
                    onNotificationClick = onNotificationClick
                )
            }
        }
    }
}