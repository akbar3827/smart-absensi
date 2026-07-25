package com.learn.smartabsensi.features.presentation.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.TopBar
import com.learn.smartabsensi.features.presentation.view_models.AttendanceHistoryUiState
import com.learn.smartabsensi.features.presentation.view_models.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun UserHistoryUiSuccess(
    hvm: HistoryViewModel,
    user: UserModel,
    onNotificationClick: (UserModel) -> Unit
) {
    val date by hvm.date.collectAsStateWithLifecycle()
    val attendanceHistoryUiState by hvm.attendance.collectAsStateWithLifecycle()
    val dateResult = hvm.dateResult(date)
    val dateNumberResult = hvm.dateNumberResult(date)
    val currentDate by remember {
        mutableStateOf(
            SimpleDateFormat(
                "yyyy-MMMM",
                Locale.getDefault()
            ).format(Date())
        )
    }
    LaunchedEffect(key1 = date) {
        hvm.getAttendance()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar(
                user = user,
                onNotificationPageClick = onNotificationClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Rekap Absensi",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Laporan kehadiran anda bulan ini",
                color = TextSecondary
            )
            Spacer(Modifier.height(20.dp))
            SwitchDate(
                hvm = hvm,
                date = date,
                dateResult = dateResult,
                currentDate = currentDate
            )
            Spacer(Modifier.height(20.dp))
            when (val state = attendanceHistoryUiState) {
                is AttendanceHistoryUiState.isLoading -> {
                    CircularProgressIndicator()
                }
                is AttendanceHistoryUiState.Error -> {
                    Text(
                        text = state.message,
                        color = TextPrimary
                    )
                }
                is AttendanceHistoryUiState.Success -> {
                    Date(
                        hvm = hvm,
                        year = dateNumberResult.split("-")[0].toInt(),
                        month = dateNumberResult.split("-")[1].toInt(),
                        attendance = state.attendances
                    )
                    Spacer(Modifier.height(18.dp))
                    ChartAttendance(
                        hvm = hvm,
                        attendances = state.attendances
                    )
                    Box(Modifier.fillMaxWidth().height(100.dp))
                }
            }
        }
    }
}