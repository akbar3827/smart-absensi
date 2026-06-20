package com.learn.smartabsensi.features.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.smartabsensi.features.data.models.AttendenceModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.data.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val userRepo = UserRepository()
    var userData by mutableStateOf(UserModel())
    var errorMessage by mutableStateOf<String?>(null)

    fun loadUser(uid: String) {
        viewModelScope.launch {
            val result = userRepo.getUser(uid = uid)
            result.onSuccess {
                userData = it
            }.onFailure {
                errorMessage = it.message
            }
        }
    }
}