package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.presentation.view_models.RegistUiState
import com.learn.smartabsensi.features.presentation.view_models.RegistViewModel
import com.ramcosta.composedestinations.generated.destinations.LoginPageDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun RegistFormStepOne(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    rvm: RegistViewModel,
    step: MutableState<Int>
) {
    val registUiState by rvm.registUiState.collectAsStateWithLifecycle()

    val nisn by rvm.nisn.collectAsStateWithLifecycle()
    var errorNisn by remember { mutableStateOf(false) }
    var errorMessageNisn by remember { mutableStateOf("") }

    val email by rvm.email.collectAsStateWithLifecycle()
    var errorEmail by remember { mutableStateOf(false) }
    var errorMessageEmail by remember { mutableStateOf("") }

    val numberPhone by rvm.numberPhone.collectAsStateWithLifecycle()
    var errorNumberPhone by remember { mutableStateOf(false) }
    var errorMessageNumberPhone by remember { mutableStateOf("") }

    val errMessage = if (registUiState is RegistUiState.Error) {
        (registUiState as RegistUiState.Error).message
    } else if (errorNisn) {
        errorMessageNisn
    } else if (errorEmail) {
        errorMessageEmail
    } else if (errorNumberPhone) {
        errorMessageNumberPhone
    } else {
        ""
    }


    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Buat Akun Baru",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Masukkan data untuk mendaftar ke SmartAbsen",
            fontSize = 14.sp,
            color = TextSecondary,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))
        TextField(
            nameField = "NISN",
            value = nisn,
            isNotMeetRequired = errorNisn,
            icon = R.drawable.ic_user_thin,
            defaultInnerTextField = "Nomor Induk Siswa Nasional",
            keyboardType = KeyboardType.Number,
            onValueChange = {
                rvm.onNisnChanged(it)
                errorNisn = false
            }
        )
        Spacer(Modifier.height(12.dp))
        TextField(
            icon = R.drawable.ic_email,
            nameField = "Email",
            value = email,
            defaultInnerTextField = "smartabsen@gmail.com",
            isNotMeetRequired = errorEmail,
            keyboardType = KeyboardType.Email,
            onValueChange = {
                rvm.onEmailChaged(it)
                errorEmail = false
            }
        )
        Spacer(Modifier.height(12.dp))
        TextField(
            icon = R.drawable.ic_call,
            nameField = "Nomor Telepon",
            value = numberPhone,
            defaultInnerTextField = "08xxxxxxxxxx",
            isNotMeetRequired = errorNumberPhone,
            keyboardType = KeyboardType.Phone,
            onValueChange = {
                rvm.onNumberPhoneChanged(it)
                errorNumberPhone = false
            }
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = errMessage,
            fontSize = 12.sp,
            color = Err,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(30.dp))
        Button(
            onClick = {
                if (nisn.isEmpty()) {
                    errorNisn = true
                    errorMessageNisn = "Nisn is required"
                } else if (nisn.length < 6) {
                    errorNisn = true
                    errorMessageNisn = "invalid nisn (min 6 character)"

                } else if (email.isEmpty()) {
                    errorEmail = true
                    errorMessageEmail = "Email is required"

                } else if (!email.contains("@gmail.com")) {
                    errorEmail = true
                    errorMessageEmail = "Invalid email format"

                } else if (numberPhone.isEmpty()) {
                    errorNumberPhone = true
                    errorMessageNumberPhone = "Number phone is required"

                } else if (!numberPhone.contains("08")) {
                    errorNumberPhone = true
                    errorMessageNumberPhone = "Invalid number phone format"

                } else if (numberPhone.length < 11 || numberPhone.length > 13) {
                    errorNumberPhone = true
                    errorMessageNumberPhone =
                        "Phone numbers can only have more than 11 numbers and less than 13 numbers"

                } else {
                    step.value += 1
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Sudah punya akun?",
                color = TextPrimary,
                fontSize = 14.sp
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Masuk disini",
                color = Indigo,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navigator.navigate(LoginPageDestination)
                }
            )
        }
    }
}