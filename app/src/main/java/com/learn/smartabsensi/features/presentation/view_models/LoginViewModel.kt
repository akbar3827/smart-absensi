package com.learn.smartabsensi.features.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.learn.smartabsensi.features.data.repositories.AuthRepository
import com.learn.smartabsensi.features.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String


sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    object Success : LoginUiState
    data class Error(val message: String) : LoginUiState
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    fun onEmailChanged(string: String) {
        _email.value = string
    }
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    fun onPasswordChanged(string: String) {
        _password.value = string
    }

    private val _loginUiState =
        MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginUiState = _loginUiState.asStateFlow()

    fun loadUser() {
        viewModelScope.launch {
            _loginUiState.update { LoginUiState.Loading }

            val result = authRepo.login(
                email = _email.value,
                password = _password.value
            )

            result.onSuccess {
                    _loginUiState.update { LoginUiState.Success }
            }.onFailure { throwable ->
                _loginUiState.update {
                    LoginUiState.Error(throwable.message ?: "")
                }
            }
        }
    }
}