package com.learn.smartabsensi.features.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.learn.smartabsensi.features.data.models.AttendanceModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.data.repositories.AttendanceRepository
import com.learn.smartabsensi.features.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val attendanceRepo: AttendanceRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    val uid = auth.currentUser?.uid

    private val _user =
        MutableStateFlow<UserHistoryUiState>(UserHistoryUiState.isLoading)
    val user = _user.asStateFlow()

    private val _attendance =
        MutableStateFlow<AttendanceHistoryUiState>(AttendanceHistoryUiState.isLoading)
    val attendance = _attendance.asStateFlow()

    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MMMM", Locale.ENGLISH)
    val dateFormatterNumber = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH)
    private val _date =
        MutableStateFlow(
            LocalDate.now()
        )
    val date = _date.asStateFlow()
    fun onDataChanged(date: LocalDate) {
        _date.value = date
    }

    fun dateResult(date: LocalDate): String {
        return date.format(dateFormatter)
    }

    fun dateNumberResult(date: LocalDate): String {
        return date.format(dateFormatterNumber)
    }

    fun getDaysInMonth(year: Int, month: Int): List<LocalDate> {
        val yearMonth = YearMonth.of(year, month)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstDay = yearMonth.atDay(1)

        val days = mutableListOf<LocalDate>()

        val leadingEmptyDays = firstDay.dayOfWeek.value - DayOfWeek.MONDAY.value
        (1..daysInMonth).forEach { day ->
            days.add(LocalDate.of(yearMonth.year, yearMonth.monthValue, day))
        }

        if (leadingEmptyDays == 0) return days

        val previousMonth = yearMonth.minusMonths(1)
        val lastDayofPreviousMonth = previousMonth.lengthOfMonth()

        ((lastDayofPreviousMonth - leadingEmptyDays + 1)..lastDayofPreviousMonth).forEachIndexed { i, day ->
            days.add(i, LocalDate.of(previousMonth.year, previousMonth.monthValue, day))
        }

        return days
    }

    val currentDate = SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    ).format(Date())

    private val period = _date.value.format(dateFormatterNumber)

    init {
        getUser()
        getAttendance()
    }

    private fun getUser() {
        viewModelScope.launch {
            val result = userRepo.getUser(uid = uid)

            result.onSuccess { user ->
                _user.update { UserHistoryUiState.Success(user) }
            }.onFailure { throwable ->
                _user.update { UserHistoryUiState.Error(throwable.message ?: "") }
            }
        }
    }

    fun getAttendance() {
        viewModelScope.launch {
            val result = attendanceRepo.getAttendances(uid ?: "", period)
            result.onSuccess { attendances ->
                _attendance.update { AttendanceHistoryUiState.Success(attendances = attendances) }
            }.onFailure { throwable ->
                _attendance.update {
                    AttendanceHistoryUiState.Error(
                        message = throwable.message ?: ""
                    )
                }
            }
        }
    }
}

sealed interface UserHistoryUiState {
    object isLoading : UserHistoryUiState
    data class Success(val user: UserModel) : UserHistoryUiState
    data class Error(val message: String) : UserHistoryUiState
}

sealed interface AttendanceHistoryUiState {
    object isLoading : AttendanceHistoryUiState
    data class Success(val attendances: List<AttendanceModel>) : AttendanceHistoryUiState
    data class Error(val message: String) : AttendanceHistoryUiState
}