package com.learn.smartabsensi.features.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.learn.smartabsensi.features.data.models.AttendanceModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.data.repositories.AttendanceRepository
import com.learn.smartabsensi.features.data.repositories.AuthRepository
import com.learn.smartabsensi.features.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val authRepo: AuthRepository,
    private val userRepository: UserRepository,
    private val attendanceRepository: AttendanceRepository
)  : ViewModel() {

    private val _userProfileUiState =
        MutableStateFlow<UserProfileUiState>(UserProfileUiState.IsLoading)
    val userProfileUiState = _userProfileUiState.asStateFlow()

    private val _attendanceProfileUiState =
        MutableStateFlow<AttendanceProfileUiState>(AttendanceProfileUiState.IsLoading)
    val attendanceProfileUiState = _attendanceProfileUiState.asStateFlow()

    private val uid = auth.currentUser?.uid

    private val period = SimpleDateFormat(
        "yyyy-MM",
        Locale.getDefault()
    ).format(Date())

    init {
        loadUser()
        loadAttendance()
    }

    fun logout() {
        authRepo.logout()
    }

    fun loadUser() {
       viewModelScope.launch {
           val result = userRepository.getUser(uid = uid)

           result.onSuccess { user ->
               _userProfileUiState.update { UserProfileUiState.Success(data = user) }
           }.onFailure { throwable ->
               _userProfileUiState.update { UserProfileUiState.Error(throwable.message ?: "") }
           }
       }
    }

    fun loadAttendance() {
        viewModelScope.launch {
            if (uid != null) {
                val result = attendanceRepository.getAttendances(uid, period)

                result.onSuccess { attendances ->
                    _attendanceProfileUiState.update { AttendanceProfileUiState.Success(data = attendances) }
                }.onFailure { throwable ->
                    _attendanceProfileUiState.update { AttendanceProfileUiState.Error(message = throwable.message ?: "") }
                }
            }
        }
    }
}

sealed interface UserProfileUiState {
    object IsLoading : UserProfileUiState
    data class Success(val data: UserModel) : UserProfileUiState
    data class Error(val message: String) : UserProfileUiState
}

sealed interface AttendanceProfileUiState {
    object IsLoading : AttendanceProfileUiState
    data class Success(val data: List<AttendanceModel>) : AttendanceProfileUiState
    data class Error(val message: String) : AttendanceProfileUiState
}