package com.learn.smartabsensi.features.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.DarkIndigo
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.view_models.ArticleHomeUiState
import com.learn.smartabsensi.features.presentation.view_models.AttendanceHomeUiState
import com.learn.smartabsensi.features.presentation.view_models.FoodHomeUiState
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import com.learn.smartabsensi.features.presentation.view_models.NewsHomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomeUiStateSuccess(
    hvm: HomeViewModel,
    userData: UserModel
) {
    val newsHomeUiState by hvm.newsHomeUiState.collectAsStateWithLifecycle()
    val attendanceUiState by hvm.attendanceHomeUiState.collectAsStateWithLifecycle()
    val articleHomeUiState by hvm.articleHomeUiState.collectAsStateWithLifecycle()
    val foodHomeUiState by hvm.foodHomeUiState.collectAsStateWithLifecycle()
    val alreadyAbsent by hvm.alreadyAttendMessage.collectAsStateWithLifecycle()
    val attendanceColor by hvm.attendanceColor.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }

    var currentTime by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    val showAlreadyAbsent= remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (isActive) {
            val date = hvm.getCurrentTime()
                .format(DateTimeFormatter.ofPattern("HH.mm"))

            currentTime = date

            delay(1000)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopBar(userData = userData)
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(horizontal = 16.dp)
                    .padding(padding)
            ) {
                item {
                    Spacer(Modifier.height(50.dp))
                    Text(
                        text = "Selamat pagi",
                        color = TextPrimary
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Indigo
                                )
                            ) {
                                append(userData.name)
                            }
                            append("")
                        },
                        color = TextPrimary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Sudah siap belajar hari ini? Jangan lupa absen yaa.",
                        color = TextSecondary
                    )
                    Spacer(Modifier.height(40.dp))
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        AbsensiCard(
                            currentTime = currentTime
                        ) {
                            showBottomSheet = it
                        }
                    }
                    if (showBottomSheet) {
                        BottomSheetAttendance(
                            hvm = hvm,
                            user = userData,
                            sheetState = sheetState,
                            currentTime = currentTime,
                            failedAttendance = showAlreadyAbsent,
                            attendanceColor = attendanceColor
                        ) {
                            showBottomSheet = it
                            if (showAlreadyAbsent.value) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(alreadyAbsent)
                                }
                                showAlreadyAbsent.value = false
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    when (val state = attendanceUiState) {
                        is AttendanceHomeUiState.IsLoading -> {
                            CircularProgressIndicator()
                        }

                        is AttendanceHomeUiState.Error -> {
                            Text("")
                        }

                        is AttendanceHomeUiState.Success -> {
                            AttendanceCard(state.data)
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Text(
                            text = "berita terbaru",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "Lihat semua",
                            color = DarkIndigo
                        )
                    }
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        when (val state = newsHomeUiState) {
                            is NewsHomeUiState.IsLoading -> {
                                item {
                                    CircularProgressIndicator()
                                }
                            }

                            is NewsHomeUiState.Error -> {
                                item {
                                    Text(text = state.message)
                                }
                            }

                            is NewsHomeUiState.Success -> {
                                state.data.forEach { news ->
                                    item {
                                        NewsCard(
                                            id = news.author,
                                            title = news.title,
                                            description = news.description,
                                            image = news.imageUrl,
                                            category = news.category
                                        )
                                    }
                                }
                            }
                        }

                        when (val state = articleHomeUiState) {
                            is ArticleHomeUiState.IsLoading -> {
                                item {
                                    CircularProgressIndicator()
                                }
                            }

                            is ArticleHomeUiState.Error -> {
                                item {
                                    Text(text = state.message)
                                }
                            }

                            is ArticleHomeUiState.Success -> {
                                state.data.articles?.forEachIndexed { index, news ->
                                    if (news != null && index < 15) {
                                        item {
                                            NewsCard(
                                                id = news.author ?: "",
                                                title = news.title ?: "",
                                                description = news.description ?: "",
                                                image = news.urlToImage ?: "",
                                                category = "info"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Text(
                            text = "Menu terlaris",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "Kantin",
                            color = DarkIndigo
                        )
                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        when (val state = foodHomeUiState) {
                            is FoodHomeUiState.IsLoading -> {
                                item {
                                    CircularProgressIndicator()
                                }
                            }

                            is FoodHomeUiState.Error -> {
                                item {
                                    Text(
                                        text = state.message
                                    )
                                }
                            }

                            is FoodHomeUiState.Success -> {
                                state.data.forEach { food ->
                                    item {
                                        FoodCard(
                                            foodModel = food
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(color = Background),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append("created by ")
                                withStyle(
                                    style = SpanStyle(
                                        color = Indigo
                                    )
                                ) {
                                    append("MOH. AKBAR KURNIAWAN")
                                }
                            },
                            color = TextPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(top = 20.dp)
        )
    }
}