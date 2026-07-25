package com.learn.smartabsensi.features.presentation.components.home.stepper

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.R
import com.learn.smartabsensi.features.data.models.VerifMethodTypeModel
import com.learn.smartabsensi.features.presentation.components.TextField
import com.learn.smartabsensi.features.presentation.components.home.VerifMethod
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel

@Composable
fun AttendanceStepTwo(
    modifier: Modifier = Modifier,
    hvm: HomeViewModel,
    color: Color,
    step: MutableState<Int>,
) {
    val attendanceCode = hvm.attendanceCode.collectAsStateWithLifecycle()

    val listVerifMethod = listOf(
        VerifMethodTypeModel(
            title = "Scan QR",
            descrip = "Scan dari guru",
            icon = R.drawable.qr_code
        ),
        VerifMethodTypeModel(
            title = "Face ID",
            descrip = "Verifikasi Wajah",
            icon = R.drawable.emoji_face
        ),
        VerifMethodTypeModel(
            title = "Manual",
            descrip = "Kode Absensi",
            icon = R.drawable.emoji_keyboard
        )
    )

    var selectedMethod by remember { mutableStateOf(listVerifMethod[0].title) }

    var isNotRequired by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listVerifMethod.forEach {
                    val isFocused = it.title == selectedMethod

                    VerifMethod(
                        modifier = Modifier.weight(1f).height(140.dp),
                        isFocused = isFocused,
                        title = it.title,
                        descrip = it.descrip,
                        icon = it.icon
                    ) { isSelected ->
                        listVerifMethod.forEach {
                            selectedMethod = isSelected
                        }
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(20.dp))
        }

        item {
                if (selectedMethod == listVerifMethod[0].title) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = Color.Black, shape = RoundedCornerShape(18.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = listVerifMethod[0].icon),
                            contentDescription = "Icon",
                            modifier = Modifier.size(50.dp).align(Alignment.Center)
                        )
                    }
                } else if (selectedMethod == listVerifMethod[1].title) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = Color.Black, shape = RoundedCornerShape(18.dp))
                    ) {
                        Image(
                            painter = painterResource(id = listVerifMethod[1].icon),
                            contentDescription = "Icon",
                            modifier = Modifier.size(50.dp).align(Alignment.Center)
                        )
                    }
                } else {
                    TextField(
                        icon = R.drawable.ic_calendar,
                        nameField = "Kode absensi",
                        value = attendanceCode.value,
                        defaultInnerTextField = "Masukkan kode absensi",
                        isNotMeetRequired = isNotRequired,
                        keyboardType = KeyboardType.Number
                    ) {
                        hvm.onAttendanceCodeChanged(it)
                    }
                }
        }

        item {
            Spacer(Modifier.height(20.dp))
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                        if (selectedMethod == listVerifMethod[2].title) {
                            if (attendanceCode.value.isEmpty()) {
                                isNotRequired = true
                            } else {
                                hvm.onAttendanceMethodChanged(selectedMethod)
                                step.value += 1
                            }
                        } else {
                            hvm.onAttendanceMethodChanged(selectedMethod)
                            step.value += 1
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