package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.features.presentation.components.ErrorResponse
import com.learn.smartabsensi.features.presentation.components.LoadingResponse
import com.learn.smartabsensi.features.presentation.components.home.UserHomeUiStateSuccess
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import com.learn.smartabsensi.features.presentation.view_models.UserHomeUiState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination<RootGraph>(start = true)
@Composable
fun HomePage(
    navigator: DestinationsNavigator,
    hvm: HomeViewModel
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
            UserHomeUiStateSuccess(
                hvm = hvm,
                userData = state.data
            )
        }
    }
}