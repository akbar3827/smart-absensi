package com.learn.smartabsensi.features.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.learn.smartabsensi.features.data.repositories.AuthRepository
import com.learn.smartabsensi.features.data.repositories.RegistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.text.isEmpty


sealed interface RegistUiState {
    object Idle : RegistUiState
    object Loading : RegistUiState
    object Success : RegistUiState
    data class Error(val message: String) : RegistUiState
}

@HiltViewModel
class RegistViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val registRepository: RegistRepository
) : ViewModel() {

    private var _nisn = MutableStateFlow("")
    val nisn = _nisn.asStateFlow()
    fun onNisnChanged(string: String) {
        _nisn.value = string
    }

    private var _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    fun onEmailChaged(string: String) {
        _email.value = string
    }

    private var _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    fun onPasswordChanged(string: String) {
        _password.value = string
    }

    private var _passwordConfirmation = MutableStateFlow("")
    val passwordConfirmation = _passwordConfirmation.asStateFlow()
    fun onPasswordConfirmationChanged(string: String) {
        _passwordConfirmation.value = string
    }

    private var _numberPhone = MutableStateFlow("")
    val numberPhone = _numberPhone.asStateFlow()
    fun onNumberPhoneChanged(string: String) {
        _numberPhone.value = string
    }

    private var _photoUrl = MutableStateFlow("https://i.pinimg.com/1200x/30/e5/18/30e5185980e8eca9a44f8647f7780d0c.jpg")
    val photoUrl = _photoUrl.asStateFlow()
    fun onPhotoUrlChanged(string: String) {
        _photoUrl.value = string
    }

    private var _fullName = MutableStateFlow("")
    val fullName = _fullName.asStateFlow()
    fun onFullNameChanged(string: String) {
        _fullName.value = string
    }

    private var _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()
    fun onNicknameChanged(string: String) {
        _nickname.value = string
    }

    private var _classRoom = MutableStateFlow("")
    val classRoom = _classRoom.asStateFlow()
    fun onClassRoomChanged(string: String) {
        _classRoom.value = string
    }

    private var _major = MutableStateFlow("")
    val nameClass = _major.asStateFlow()
    fun onNameClassChanged(string: String) {
        _major.value = string
    }

    private var _gender = MutableStateFlow("")
    val gender = _gender.asStateFlow()
    fun onGenderChanged(string: String) {
        _gender.value = string
    }

    private var _registUiState =
        MutableStateFlow<RegistUiState>(RegistUiState.Idle)
    val registUiState = _registUiState.asStateFlow()

    val currentTime = SimpleDateFormat(
        "yyyy-MM-dd:HH-mm-ss",
        Locale.getDefault()
    ).format(Date())


    fun register() {
        viewModelScope.launch {
            _registUiState.update { RegistUiState.Loading }


            val result = authRepository.register(
                email = email.value,
                password = password.value,
            )

            result.onSuccess {
                registRepository.setUser(
                    uid = it.uid,
                    email = it.email,
                    name = fullName.value,
                    nickname = nickname.value,
                    nisn = nisn.value,
                    photoUrl = photoUrl.value,
                    classRoom = classRoom.value,
                    major = nameClass.value,
                    gender = gender.value,
                    createdAt = currentTime
                )
                _registUiState.update { RegistUiState.Success }
            }.onFailure { throwable ->
                _registUiState.update {
                    RegistUiState.Error(throwable.message ?: "")
                }
            }
        }
    }
}