package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.presentation.view_models.RegistUiState
import com.learn.smartabsensi.features.presentation.view_models.RegistViewModel

@Composable
fun RegistFormStepTwo(
    modifier: Modifier = Modifier,
    rvm: RegistViewModel,
    step: MutableState<Int>
) {
    val opsiPilihanKelas = listOf("Pilih", "X", "XI", "XII")
    val opsiPilihanNamaKelas = listOf(
        "Pilih",
        "A",
        "B",
        "C",
        "D",
        "E",
        "F"
    )
    val registUiState by rvm.registUiState.collectAsStateWithLifecycle()

    val fullName by rvm.fullName.collectAsStateWithLifecycle()
    var errorFullName by remember { mutableStateOf(false) }
    var errorMessageFullName by remember { mutableStateOf("") }

    val nickname by rvm.nickname.collectAsStateWithLifecycle()
    var errorNickname by remember { mutableStateOf(false) }
    var errorNicknameMessage by remember { mutableStateOf("") }

    val classRoom by rvm.classRoom.collectAsStateWithLifecycle()
    var errorClassRoom by remember { mutableStateOf(false) }
    var errorMessageClassRoom by remember { mutableStateOf("") }

    val className by rvm.nameClass.collectAsStateWithLifecycle()
    var errorClassName by remember { mutableStateOf(false) }
    var errorMessageClassName by remember { mutableStateOf("") }

    val gender by rvm.gender.collectAsStateWithLifecycle()
    var errorGender by remember { mutableStateOf(false) }
    var errorMessageGender by remember { mutableStateOf("") }

    val errMessage = if (registUiState is RegistUiState.Error) {
        (registUiState as RegistUiState.Error).message
    } else if (errorFullName) {
        errorMessageFullName
    } else if (errorClassRoom) {
        errorMessageClassRoom
    } else if (errorClassName) {
        errorMessageClassName
    } else if (errorGender) {
        errorMessageGender
    } else {
        ""
    }


    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(150.dp)
        ) {
            AsyncImage(
                model = "https://i.pinimg.com/1200x/30/e5/18/30e5185980e8eca9a44f8647f7780d0c.jpg",
                contentDescription = "profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        ambientColor = Indigo,
                        spotColor = Indigo.copy(alpha = 0.5f)
                    )
                    .padding(3.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = -4.dp, y = -10.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        ambientColor = Indigo,
                        spotColor = Indigo.copy(alpha = 0.5f)
                    )
                    .clip(CircleShape)
                    .background(Indigo),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        TextField(
            icon = R.drawable.ic_user_thin,
            nameField = "Nama lengkap",
            value = fullName,
            defaultInnerTextField = "Nama lengkap sesuai rapor",
            isNotMeetRequired = errorFullName,
            onValueChange = {
                rvm.onFullNameChanged(it)
                errorFullName = false
            }
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            icon = R.drawable.ic_user_thin,
            nameField = "Nama panggilan",
            value = nickname,
            defaultInnerTextField = "Nama panggilan anda sesuai dengan rapor",
            isNotMeetRequired = errorNickname,
            onValueChange = {
                rvm.onNicknameChanged(it)
                errorNickname = false
            }
        )
        Spacer(Modifier.height(16.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Selection(
                modifier = Modifier.weight(1f),
                fieldName = "Kelas",
                opsiPilihan = opsiPilihanKelas,
                selectedOption = classRoom,
                isNotMeetRequirement = errorClassRoom
            ) {
                rvm.onClassRoomChanged(it)
                errorClassRoom = false
            }
            Selection(
                modifier = Modifier.weight(1f),
                fieldName = "Nama kelas",
                opsiPilihan = opsiPilihanNamaKelas,
                selectedOption = className,
                isNotMeetRequirement = errorClassName
            ) {
                rvm.onNameClassChanged(it)
                errorClassName = false
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Jenis kelamin",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = TextPrimary,
            modifier = Modifier.fillMaxWidth()
        )
        Gender {
            rvm.onGenderChanged(it)
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = errMessage,
            fontSize = 12.sp,
            color = Err,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    step.value -= 1
                },
                modifier = Modifier
                    .weight(0.7f)
                    .height(50.dp)
                    .shadow(
                        elevation = 10.dp,
                        clip = true,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = Color.Black,
                        spotColor = Color.Black.copy(alpha = 0.2f)
                    )
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(16.dp),
                        color = Color.Gray.copy(alpha = 0.2f)
                    ),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Kembali",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Button(
                onClick = {
                    if (fullName.isEmpty()) {
                        errorFullName = true
                        errorMessageFullName = "name is required"

                    } else if (nickname.isEmpty()) {
                        errorNickname = true
                        errorNicknameMessage = "nickname is required"

                    } else if (classRoom.isEmpty() || classRoom == opsiPilihanKelas[0]) {
                        errorClassRoom = true
                        errorMessageClassRoom = "Class is required"

                    } else if (className.isEmpty() || className == opsiPilihanNamaKelas[0]) {
                        errorClassName = true
                        errorMessageClassName = "Class name is required"

                    } else if (gender.isEmpty()) {
                        errorGender = true
                        errorMessageGender = "Gender is required"

                    } else {
                        step.value += 1
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .shadow(
                        elevation = 10.dp,
                        clip = true,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = Indigo,
                        spotColor = Indigo
                    ),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Indigo,
                    contentColor = Color.White
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
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
    }
}