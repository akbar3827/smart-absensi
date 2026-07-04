package com.learn.smartabsensi.features.presentation.components.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.home.stepper.AttendanceStepOne
import com.learn.smartabsensi.features.presentation.components.home.stepper.AttendanceStepThree
import com.learn.smartabsensi.features.presentation.components.home.stepper.AttendanceStepTwo
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetAttendance(
    modifier: Modifier = Modifier,
    hvm: HomeViewModel,
    user: UserModel,
    currentTime: String,
    sheetState: SheetState,
    attendanceColor: Color,
    failedAttendance: MutableState<Boolean>,
    onBottomSheetChanged: (Boolean) -> Unit
) {
    val kindOfAttendance by hvm.kindOfAttendance.collectAsStateWithLifecycle()
    val attendanceMethod by hvm.attendanceMethod.collectAsStateWithLifecycle()

    val step = remember { mutableStateOf(0) }

    val scope = rememberCoroutineScope()

    val targetWeightBar2 = if (step.value >= 1) 1f else 0f
    val targetWeightBar3 = if (step.value >= 2) 1f else 0f

    val animatedWeightbar2 by animateFloatAsState(
        targetValue = targetWeightBar2,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 100
        )
    )
    val animatedWeightbar3 by animateFloatAsState(
        targetValue = targetWeightBar3,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 100
        )
    )



    ModalBottomSheet(
        onDismissRequest = {
            onBottomSheetChanged(false)
        },
        sheetState = sheetState,
        containerColor = Color.White
    ) {
        Row(modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .background(
                        color = Indigo,
                        shape = CircleShape
                    )
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .background(
                        color = Color.Gray.copy(alpha = 0.2f),
                        shape = CircleShape
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animatedWeightbar2)
                        .height(3.dp)
                        .background(
                            color = Indigo,
                            shape = CircleShape
                        )
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .background(
                        color = Color.Gray.copy(alpha = 0.2f),
                        shape = CircleShape
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animatedWeightbar3)
                        .height(3.dp)
                        .background(
                            color = Indigo,
                            shape = CircleShape
                        )
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = if (step.value == 0) "Jenis Absensi"
                        else if (step.value == 1) "Metode Verifikasi"
                        else "Foto Dokumentasi",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = if (step.value == 0) "Pilih jenis kehadiranmu saat ini"
                        else if (step.value == 1) "Pilih cara verifikasi absensi"
                        else "Upload foto dokumentasi sebagai bukti",
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                }
                IconButton(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            onBottomSheetChanged(false)
                        }
                    },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Close bottom sheet",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(
                                RoundedCornerShape(12.dp)
                            )
                            .border(
                                width = 0.dp,
                                color = Color.LightGray.copy(alpha = 0.6f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(Color.LightGray.copy(alpha = 0.1f))
                            .padding(8.dp),
                        tint = TextSecondary,
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(IndigoLigth.copy(alpha = 0.1f))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    tint = Indigo,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .padding(6.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "SMA Muhammadiyah 3 Jember",
                    color = Indigo,
                    fontSize = 14.sp
                )
                Spacer(Modifier.width(6.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_point),
                    contentDescription = null,
                    tint = Indigo,
                    modifier = Modifier.size(4.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = currentTime,
                    color = Indigo,
                    fontSize = 14.sp
                )
            }
            Spacer(Modifier.height(20.dp))
            if (step.value == 0) {
                AttendanceStepOne(
                    hvm = hvm,
                    step = step,
                    selectedOption = kindOfAttendance,
                    attendanceColor = attendanceColor
                )
            } else if (step.value == 1) {
                AttendanceStepTwo(
                    hvm = hvm,
                    color = attendanceColor,
                    step = step,
                )
            } else {
                AttendanceStepThree(
                    hvm = hvm,
                    user = user,
                    status = kindOfAttendance,
                    scope = scope,
                    color = attendanceColor,
                    step = step,
                    sheetState = sheetState,
                    failedAttendance = failedAttendance,
                    kindOfAttendance = kindOfAttendance,
                    attendanceMethod = attendanceMethod
                ) {
                    onBottomSheetChanged(it)
                    hvm.loadAttendance()
                }
            }
        }
    }
}