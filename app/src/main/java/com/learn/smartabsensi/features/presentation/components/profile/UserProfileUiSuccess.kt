package com.learn.smartabsensi.features.presentation.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.core.themes.Amber
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.Orange
import com.learn.smartabsensi.core.themes.Purple
import com.learn.smartabsensi.core.themes.Teal
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.StatAttendanceProfileModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.view_models.AttendanceProfileUiState
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel

@Composable
fun UserProfileUiSuccess(
    modifier: Modifier = Modifier,
    onEditProfilePageClick: (UserModel) -> Unit,
    onNotificationPagePageClick: (UserModel) -> Unit,
    onChangePasswordPagePageClick: (UserModel) -> Unit,
    onLoginPageClick: () -> Unit,
    pvm: ProfileViewModel,
    user: UserModel
) {
    val attendanceProfileUIState by pvm.attendanceProfileUiState.collectAsStateWithLifecycle()

    var showBox = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopBarProfile(user = user)
        }

        item {
            Spacer(Modifier.height(16.dp))
            Text(
                text = user.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Kelas ${user.classRoom}${user.className}",
                color = Indigo
            )
            Text(
                text = "NISN: ${user.nisn}",
                color = TextSecondary
            )
        }

        item {
            Spacer(Modifier.height(20.dp))
            when (val state = attendanceProfileUIState) {
                is AttendanceProfileUiState.IsLoading -> {
                    CircularProgressIndicator()
                }

                is AttendanceProfileUiState.Error -> {
                    Text(state.message)
                }

                is AttendanceProfileUiState.Success -> {
                    var hadir = 0
                    var sakit = 0
                    var izin = 0
                    var dispen = 0
                    var alfa = 0
                    state.data.forEach {
                        if (it.status.lowercase().trim() == "hadir") {
                            hadir += 1
                        } else if (it.status.lowercase().trim() == "sakit") {
                            sakit += 1
                        } else if (it.status.lowercase().trim() == "izin") {
                            izin += 1
                        } else if (it.status.lowercase().trim() == "dispen") {
                            dispen += 1
                        } else {
                            alfa += 1
                        }
                    }
                    val total = hadir + sakit + izin + dispen + alfa
                    val percentage = if (total >= 1) {
                        (hadir.toFloat() / total * 100).toInt()
                    } else {
                        0
                    }
                    AchievementCards(percentage = percentage)

                    Spacer(Modifier.height(16.dp))

                    val listStatAttendanceProfile = listOf(
                        StatAttendanceProfileModel(
                            stat = hadir,
                            color = Teal,
                            category = "Hadir"
                        ),
                        StatAttendanceProfileModel(
                            stat = sakit,
                            color = Amber,
                            category = "Sakit"
                        ),
                        StatAttendanceProfileModel(
                            stat = izin,
                            color = Orange,
                            category = "Izin"
                        ),
                        StatAttendanceProfileModel(
                            stat = dispen,
                            color = Purple,
                            category = "Dispen"
                        ),
                        StatAttendanceProfileModel(
                            stat = alfa,
                            color = Err,
                            category = "Alfa"
                        ),
                        StatAttendanceProfileModel(
                            stat = total,
                            color = Indigo,
                            category = "Total"
                        ),
                    )
                    StartCardProfile(
                        percentage = percentage,
                        listAttendance = listStatAttendanceProfile
                    )
                }
            }
        }

        item {
            Spacer(Modifier.height(20.dp))
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "PENGATURAN AKUN",
                    color = TextSecondary.copy(alpha = 0.8f),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                AccountSetting(
                    user = user,
                    onEditProfilePageClick = onEditProfilePageClick,
                    onNotificationPagePageClick = onNotificationPagePageClick,
                    onChangePasswordPagePageClick = onChangePasswordPagePageClick
                )

                Spacer(Modifier.height(20.dp))
                Text(
                    text = "PENGATURAN AKUN",
                    color = TextSecondary.copy(alpha = 0.8f),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Preference()

                Spacer(Modifier.height(20.dp))
                Text(
                    text = "LAINNYA",
                    color = TextSecondary.copy(alpha = 0.8f),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Others(emptyScreen = { it })

                Spacer(Modifier.height(20.dp))
                LogOut(pvm = pvm, showBox = showBox)
            }
        }

        item {
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
    if (showBox.value) {
        FloatLogoutBox(
            onLoginPageClick = onLoginPageClick,
            pvm = pvm
        ) {
            showBox.value = it
        }
    }
}