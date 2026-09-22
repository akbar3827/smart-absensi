package com.learn.smartabsensi.features.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.learn.smartabsensi.features.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeProfileViewModel @Inject constructor(
    val userRepo: UserRepository,
    val auth: FirebaseAuth
) : ViewModel() {
    
    val uid = auth.uid
    private val _fullName =
        MutableStateFlow("")
    val fullName = _fullName.asStateFlow()
    fun fullNameChanged(string: String) {
        _fullName.value = string
    }

    private val _email =
        MutableStateFlow("")
    val email = _email.asStateFlow()
    fun emailChanged(string: String) {
        _email.value = string
    }

    private val _numberphone =
        MutableStateFlow("")
    val numberphone = _numberphone.asStateFlow()
    fun numberphoneChanged(string: String) {
        _numberphone.value = string
    }

    private val _nisn =
        MutableStateFlow("")
    val nisn = _nisn.asStateFlow()
    fun nisnChanged(string: String) {
        _nisn.value = string
    }

    private val _class =
        MutableStateFlow("")
    val classs = _class.asStateFlow()
    fun classChanged(string: String) {
        _class.value = string
    }

    private val _major =
        MutableStateFlow("")
    val major = _major.asStateFlow()
    fun majorChanged(string: String) {
        _major.value = string
    }

    private val _gender =
        MutableStateFlow("")
    val gender = _gender.asStateFlow()
    fun genderChanged(string: String) {
        _gender.value = string
    }

    private val _religion =
        MutableStateFlow("")
    val religion = _religion.asStateFlow()
    fun religionChanged(string: String) {
        _religion.value = string
    }

    private val _address =
        MutableStateFlow("")
    val address = _address.asStateFlow()
    fun addressChanged(string: String) {
        _address.value = string
    }

    private val _biodata =
        MutableStateFlow("")
    val biodata = _biodata.asStateFlow()
    fun biodataChanged(string: String) {
        _biodata.value = string
    }
    
    private val _uiState = MutableStateFlow<UserUpdateUiState>(UserUpdateUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun updateProfile(onSuccess: () -> Unit) {
        val uid = auth.uid ?: return
        
        val updateData = mutableMapOf<String, Any>()
        if (_fullName.value.isNotEmpty()) updateData["name"] = _fullName.value
        if (_email.value.isNotEmpty()) updateData["email"] = _email.value
        if (_nisn.value.isNotEmpty()) updateData["nisn"] = _nisn.value
        if (_class.value.isNotEmpty()) updateData["classRoom"] = _class.value
        if (_major.value.isNotEmpty()) updateData["className"] = _major.value
        if (_gender.value.isNotEmpty()) updateData["gender"] = _gender.value
        if (_address.value.isNotEmpty()) updateData["address"] = _address.value
        if (_biodata.value.isNotEmpty()) updateData["biodata"] = _biodata.value
        if (_numberphone.value.isNotEmpty()) updateData["numberphone"] = _numberphone
        if (_religion.value.isNotEmpty()) updateData["religion"] = _religion

        if (updateData.isEmpty()) return

        _uiState.value = UserUpdateUiState.Loading
        viewModelScope.launch {
            val result = userRepo.updateUserData(uid, updateData)
            result.onSuccess {
                _uiState.value = UserUpdateUiState.Success
                onSuccess()
            }.onFailure { e ->
                _uiState.value = UserUpdateUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed interface UserUpdateUiState {
    object Idle : UserUpdateUiState
    object Loading : UserUpdateUiState
    object Success : UserUpdateUiState
    data class Error(val message: String) : UserUpdateUiState
}