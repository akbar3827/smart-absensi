package com.learn.smartabsensi.features.presentation.components.home.stepper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Amber
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.Orange
import com.learn.smartabsensi.core.themes.Purple
import com.learn.smartabsensi.core.themes.Teal
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.AttendanceTypeModel
import com.learn.smartabsensi.features.presentation.components.home.AttendanceNoteInput
import com.learn.smartabsensi.features.presentation.components.home.KindOfAttendance
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceStepOne(
    modifier: Modifier = Modifier,
    hvm: HomeViewModel,
    step: MutableState<Int>,
    selectedOption: String,
    attendanceColor: Color
) {
    val isNotSelected = remember { mutableStateOf(false) }
    val listOfAttendance = listOf(
        AttendanceTypeModel(
            attendance = "Hadir",
            information = "Saya hadir di sekolah hari ini",
            requirement = "Kehadiran Normal",
            icon = R.drawable.ic_hadir,
            color = Teal
        ),
        AttendanceTypeModel(
            attendance = "Izin",
            information = "Tidak hadir dengan alasan tertentu",
            "Perlu Surat Izin",
            icon = R.drawable.ic_sakit,
            color = Amber
        ),
        AttendanceTypeModel(
            attendance = "Sakit",
            information = "Tidak hadir karena kondisi kesehatan",
            requirement = "Perlu Surat Dokter",
            icon = R.drawable.ic_izin,
            color = Orange
        ),
        AttendanceTypeModel(
            attendance = "Dispen",
            information = "Dispensasi kegiatan resmi sekolah",
            requirement = "Disetujui Wali Kelas",
            icon = R.drawable.ic_dispen,
            color = Purple
        )
    )


    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth().height(600.dp),
        columns = GridCells.Fixed(2)
    ) {
        item(
            span = {
                GridItemSpan(2)
            }
        ) {
            Text(
                text = "JENIS KEHADIRAN",
                color = TextSecondary,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(listOfAttendance.size) { i ->
            val attendance = listOfAttendance[i]

            val onFocused = attendance.attendance == selectedOption

            KindOfAttendance(
                hvm = hvm,
                onFocused = onFocused,
                isNotSelected = isNotSelected,
                attendance = attendance,
                selectedOption = selectedOption
            )
        }

        item(span = { GridItemSpan(2) }) {
            Column {
                Text(
                    text = "Keterangan (Opsional)",
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 14.dp)
                )

                Spacer(Modifier.height(4.dp))
                AttendanceNoteInput(hvm = hvm)
                Spacer(Modifier.height(30.dp))

                Button(
                    onClick = {
                        if (selectedOption.isEmpty()) {
                            isNotSelected.value = true
                        } else {
                            step.value += 1
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = attendanceColor,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                        .height(50.dp)
                        .shadow(
                            elevation = 10.dp,
                            clip = true,
                            shape = RoundedCornerShape(16.dp),
                            ambientColor = Indigo,
                            spotColor = Indigo
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lanjutkan",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    }
}