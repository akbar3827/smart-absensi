package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.ErrorResponse
import com.learn.smartabsensi.features.presentation.components.LoadingResponse
import com.learn.smartabsensi.features.presentation.components.canteen.UserCanteenUiSuccess
import com.learn.smartabsensi.features.presentation.view_models.CanteenFoodUiState
import com.learn.smartabsensi.features.presentation.view_models.CanteenUserUiState
import com.learn.smartabsensi.features.presentation.view_models.CanteenViewModel

@Composable
fun CanteenPage(
    cvm: CanteenViewModel = viewModel(),
    onNotificationPageClick: (UserModel) -> Unit
) {
    val canteenUserUiState by cvm.user.collectAsStateWithLifecycle()
    val canteenFoodUiState by cvm.food.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        when (val user = canteenUserUiState) {
            is CanteenUserUiState.isLoading -> LoadingResponse()
            is CanteenUserUiState.Error -> ErrorResponse()
            is CanteenUserUiState.Success -> {
                when (val food = canteenFoodUiState) {
                    is CanteenFoodUiState.isLoading -> LoadingResponse()
                    is CanteenFoodUiState.Error -> ErrorResponse()
                    is CanteenFoodUiState.Success -> UserCanteenUiSuccess(
                        cvm = cvm,
                        user = user.user,
                        foods = food.food,
                        onNotificationPageClick = onNotificationPageClick
                    )
                }
            }
        }
    }
}