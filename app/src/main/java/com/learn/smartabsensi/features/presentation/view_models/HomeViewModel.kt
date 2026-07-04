package com.learn.smartabsensi.features.presentation.view_models

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.learn.smartabsensi.core.networkings.ApiConfig
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.features.data.models.ArticleResponse
import com.learn.smartabsensi.features.data.models.AttendanceModel
import com.learn.smartabsensi.features.data.models.FoodModel
import com.learn.smartabsensi.features.data.models.NewsModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.data.repositories.AttendanceRepository
import com.learn.smartabsensi.features.data.repositories.AuthRepository
import com.learn.smartabsensi.features.data.repositories.FoodRepository
import com.learn.smartabsensi.features.data.repositories.NewsRepository
import com.learn.smartabsensi.features.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val attendanceRepository: AttendanceRepository,
    private val auth: FirebaseAuth,
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository,
    private val foodRepository: FoodRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val uid = auth.currentUser?.uid
    fun getCurrentTime(): ZonedDateTime {
        return ZonedDateTime.now()
    }
    private val period = SimpleDateFormat(
        "yyyy-MM",
        Locale.getDefault()
    ).format(Date())

    private val date = SimpleDateFormat(
        "yyyy-MM-dd HH:mm",
        Locale.getDefault()
    ).format(Date())

    private val createdAt = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault()
    ).format(Date())

    private var _foodHomeUiState =
        MutableStateFlow<FoodHomeUiState>(FoodHomeUiState.IsLoading)
    val foodHomeUiState = _foodHomeUiState.asStateFlow()

    private var _userHomeUiState =
        MutableStateFlow<UserHomeUiState>(UserHomeUiState.IsLoading)
    val userHomeUiState = _userHomeUiState.asStateFlow()

    private var _attendanceHomeUiState =
        MutableStateFlow<AttendanceHomeUiState>(AttendanceHomeUiState.IsLoading)
    val attendanceHomeUiState = _attendanceHomeUiState.asStateFlow()

    private var _articleHomeUiState =
        MutableStateFlow<ArticleHomeUiState>(ArticleHomeUiState.IsLoading)
    val articleHomeUiState = _articleHomeUiState.asStateFlow()

    private var _newsHomeUiState =
        MutableStateFlow<NewsHomeUiState>(NewsHomeUiState.IsLoading)
    val newsHomeUiState = _newsHomeUiState.asStateFlow()

    private var _errorMessageAttendance =
        MutableStateFlow("")
    val alreadyAbsent = _errorMessageAttendance.asStateFlow()

    private var _attendanceCode =
        MutableStateFlow("")
    val attendanceCode = _attendanceCode.asStateFlow()
    fun onAttendanceCodeChanged(string: String) {
        _attendanceCode.value = string
    }

    private var _attendanceColor = MutableStateFlow<Color>(Indigo)
    val attendanceColor = _attendanceColor.asStateFlow()
    fun onAttendanceColorChanged(color: Color) {
        _attendanceColor.value = color
    }

    private var _kindOfAttendance =
        MutableStateFlow("")
    val kindOfAttendance = _kindOfAttendance.asStateFlow()
    fun onKindOfAttendanceChanged(string: String) {
        _kindOfAttendance.value = string
    }
    private var _attendanceMethod =
        MutableStateFlow("")
    val attendanceMethod = _attendanceMethod.asStateFlow()
    fun onAttendanceMethodChanged(string: String) {
        _attendanceMethod.value = string
    }

    private var _attendanceNote =
        MutableStateFlow("")
    val attendanceNote = _attendanceNote.asStateFlow()
    fun onAttendanceNoteChanged(string: String) {
        _attendanceNote.value = string
    }

    init {
        loadUser()
        loadNews()
        loadArticles()
        loadAttendance()
        loadFood()
    }

    fun reload() {
        authRepository.reload()
    }

    fun loadUser() {
        viewModelScope.launch {

            val result = userRepository.getUser(uid = uid)

            result.onSuccess { userModel ->
                _userHomeUiState.update { UserHomeUiState.Success(userModel) }
            }.onFailure { throwable ->
                _userHomeUiState.update { UserHomeUiState.Error(throwable.message ?: "") }
            }
        }
    }

    fun loadArticles() {
        val q = "apple"
        val from = "2026-06-14"
        val to = getCurrentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val sortBy = "popularity"
        val apiKey = "eb0c87479cef41298b0f5948e67f88b9"

        viewModelScope.launch {
            try {
                val response = ApiConfig.apiService.getCurrentData(
                    q = q,
                    from = from,
                    to = to,
                    sortBy = sortBy,
                    apiKey = apiKey
                )
                _articleHomeUiState.update {
                    ArticleHomeUiState.Success(
                        data = response
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _articleHomeUiState.update {
                    ArticleHomeUiState.Error(
                        message = e.message ?: ""
                    )
                }
            }
        }
    }

    fun loadNews() {
        viewModelScope.launch {
            val result = newsRepository.getNews()
            result.onSuccess {newsModels ->
                _newsHomeUiState.update { NewsHomeUiState.Success(newsModels) }
            }.onFailure {throwable ->
                _newsHomeUiState.update { NewsHomeUiState.Error(message = throwable.message ?: "") }
            }
        }
    }

    fun loadAttendance() {
        viewModelScope.launch {
            if (uid != null) {
                val result = attendanceRepository.getAttendance(uid, period)

                result.onSuccess {attendanceModel ->
                    _attendanceHomeUiState.update { AttendanceHomeUiState.Success(data = attendanceModel) }
                }.onFailure { throwable ->
                    _attendanceHomeUiState.update { AttendanceHomeUiState.Error(message = throwable.message ?: "") }
                }
            }
        }
    }
    fun setAttendance(
        name: String,
        status: String,
        classRoom: String
    ) {
        viewModelScope.launch {
            if (uid != null) {
                attendanceRepository.setAttendance(
                    uid = uid,
                    name = name,
                    classRoom = classRoom,
                    status = status,
                    period = period,
                    date = date,
                    createdAt = createdAt
                ) {
                    _errorMessageAttendance.value = it
                }
            }
        }
    }

    fun loadFood() {
        viewModelScope.launch {
            val result = foodRepository.getFood()
            result.onSuccess { foodModels ->
                _foodHomeUiState.update { FoodHomeUiState.Success(data = foodModels) }
            }
            result.onFailure {throwable ->
                _foodHomeUiState.update { FoodHomeUiState.Error(message = throwable.message ?: "") }
            }
        }
    }
}



sealed interface FoodHomeUiState {
    object IsLoading : FoodHomeUiState
    data class Success(val data: List<FoodModel>) : FoodHomeUiState
    data class Error(val message: String) : FoodHomeUiState
}


sealed interface UserHomeUiState {
    object IsLoading : UserHomeUiState
    data class Success(val data: UserModel) : UserHomeUiState
    data class Error(val message: String) : UserHomeUiState
}

sealed interface AttendanceHomeUiState {
    object IsLoading : AttendanceHomeUiState
    data class Success(val data: List<AttendanceModel>) : AttendanceHomeUiState
    data class Error(val message: String) : AttendanceHomeUiState
}

sealed interface ArticleHomeUiState {
    object IsLoading : ArticleHomeUiState
    data class Success(val data: ArticleResponse) : ArticleHomeUiState
    data class Error(val message: String) : ArticleHomeUiState
}

sealed interface NewsHomeUiState {
    object IsLoading : NewsHomeUiState
    data class Success(val data: List<NewsModel>) : NewsHomeUiState
    data class Error(val message: String) : NewsHomeUiState
}