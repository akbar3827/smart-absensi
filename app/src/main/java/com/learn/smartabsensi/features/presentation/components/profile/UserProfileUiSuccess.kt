package com.learn.smartabsensi.features.presentation.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Amber
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.DarkIndigo
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.core.themes.Orange
import com.learn.smartabsensi.core.themes.Primary
import com.learn.smartabsensi.core.themes.Purple
import com.learn.smartabsensi.core.themes.Teal
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.core.themes.YellowDark
import com.learn.smartabsensi.features.data.models.AccountSetting
import com.learn.smartabsensi.features.data.models.StatAttendanceProfileModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.view_models.AttendanceProfileUiState
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun UserProfileUiSuccess(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    pvm: ProfileViewModel,
    user: UserModel
) {
    val attendanceProfileUIState by pvm.attendanceProfileUiState.collectAsStateWithLifecycle()

    var showBox by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(IndigoLigth)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "bell",
                    tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = -30.dp, y = -30.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = 0.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(color = Color.White.copy(alpha = 0.1f))
                        .padding(12.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Background)
                        .align(Alignment.BottomCenter)
                )
                AsyncImage(
                    model = user.photoUrl,
                    contentDescription = "photo profil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .shadow(
                            elevation = 8.dp,
                            clip = false,
                            shape = CircleShape,
                            ambientColor = DarkIndigo,
                            spotColor = DarkIndigo.copy(alpha = 0.8f)
                        )
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = Indigo,
                            shape = CircleShape
                        )
                        .align(Alignment.BottomCenter)
                )
            }
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
                text = "Kelas ${user.classRoom} ${user.className}",
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
                            color = Teal,
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
                val listAccountSet = listOf(
                    AccountSetting(
                        icon = R.drawable.ic_edit_username,
                        name = "Ubah Profil",
                        color = Purple,
                        description = "Nama, foto, dan info diri",
                    ),
                    AccountSetting(
                        icon = R.drawable.ic_notification,
                        name = "Notifikasi",
                        color = YellowDark,
                        description = "Atur preferensi notifikasi",
                    ),
                    AccountSetting(
                        icon = R.drawable.ic_passwordd,
                        name = "Ubah Profil",
                        color = Teal,
                        description = "Terakhir diubah 30 hari lalu",
                    )
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(18.dp),
                            clip = false,
                            ambientColor = Color.Gray,
                            spotColor = Color.Gray.copy(alpha = 0.4f)
                        )
                        .border(
                            width = 0.dp,
                            color = Color.Gray.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(18.dp)
                        )
                        .background(color = Color.White, shape = RoundedCornerShape(18.dp))
                        .padding(horizontal = 16.dp)
                ) {
                    listAccountSet.forEachIndexed { index, setting ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Icon(
                                    painter = painterResource(id = setting.icon),
                                    contentDescription = setting.description,
                                    tint = setting.color,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(color = setting.color.copy(alpha = 0.1f),
                                            shape = RoundedCornerShape(12.dp)
                                        ).padding(8.dp)
                                )
                                Spacer(Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = setting.name,
                                        color = TextPrimary,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Text(
                                        text = setting.description,
                                        color = TextSecondary,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                            Icon(
                                painter = painterResource(id = setting.arrowRight),
                                contentDescription = "Arrow In",
                                tint = TextSecondary.copy(alpha = 0.5f),
                                modifier = Modifier.size(10.dp)
                            )
                        }
                        if (index < listAccountSet.size - 1) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                                    .fillMaxWidth()
                                    .height(0.3.dp)
                                    .background(Color.Gray.copy(alpha = 0.5f))
                            )
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))
                Text(
                    text = "PENGATURAN AKUN",
                    color = TextSecondary.copy(alpha = 0.8f),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(18.dp),
                            clip = false,
                            ambientColor = Color.Gray,
                            spotColor = Color.Gray.copy(alpha = 0.4f)
                        )
                        .border(
                            width = 0.dp,
                            color = Color.Gray.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(18.dp)
                        )
                        .background(color = Color.White, shape = RoundedCornerShape(18.dp))
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_globe),
                                contentDescription = "bahasa",
                                tint = Primary,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(color = Primary.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(12.dp)
                                    ).padding(8.dp)
                            )
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Bahasa",
                                    color = TextPrimary,
                                    fontWeight = FontWeight.SemiBold,
                                )
                                Text(
                                    text = "Pilih bahasa antarmuka",
                                    color = TextSecondary,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "Arrow In",
                            tint = TextSecondary.copy(alpha = 0.5f),
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(18.dp),
                            clip = false,
                            ambientColor = Err,
                            spotColor = Err.copy(alpha = 0.4f)
                        )
                        .border(
                            width = 0.dp,
                            color = Err.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(18.dp)
                        )
                        .background(color = Err.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(18.dp)
                        )
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .clickable {
                            showBox = true
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "Logout",
                        tint = Err,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = Err.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(12.dp)
                            ).padding(8.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "Keluar",
                        color = Err,
                        fontWeight = FontWeight.Bold
                    )
                }
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
    if (showBox) {
        FloatLogoutBox(
            navigator = navigator,
            pvm = pvm
        ) {
            showBox = it
        }
    }
}