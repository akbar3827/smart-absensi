package com.learn.smartabsensi.features.presentation.components.home.stepper

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.home.KindOfProof
import com.learn.smartabsensi.features.presentation.components.home.Proof
import com.learn.smartabsensi.features.presentation.components.home.SummaryAttendance
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceStepThree(
    modifier: Modifier = Modifier,
    hvm: HomeViewModel,
    step: MutableState<Int>,
    color: Color,
    user: UserModel,
    kindOfAttendance: String,
    attendanceMethod: String,
    status: String,
    scope: CoroutineScope,
    sheetState: SheetState,
    onShowBottomSheetChanged: (Boolean) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Foto Selfie (Opsional)",
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Opsional",
                    color = color,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = color.copy(alpha = 0.1f), shape = CircleShape)
                        .padding(horizontal = 14.dp, vertical = 4.dp)
                )
            }

            Spacer(Modifier.height(16.dp))
            Proof(color = color)
            Spacer(Modifier.height(16.dp))
            KindOfProof()
            Spacer(Modifier.height(20.dp))
            SummaryAttendance(
                color = color,
                kindOfAttendance = kindOfAttendance,
                attendanceMethod = attendanceMethod
            )
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        step.value -= 1
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.DarkGray
                    ),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .weight(0.7f)
                        .height(50.dp)
                        .border(
                            width = 0.dp,
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(18.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                    Text(
                        text = "Kembali",
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        hvm.setAttendance(
                            name = user.name,
                            status = status,
                            classRoom = user.classRoom
                        )
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            onShowBottomSheetChanged(false)
                        }
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .shadow(
                            elevation = 10.dp,
                            clip = true,
                            shape = RoundedCornerShape(18.dp),
                            ambientColor = color,
                            spotColor = color.copy(alpha = 0.5f)
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Lanjutkan",
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
        }
    }
}