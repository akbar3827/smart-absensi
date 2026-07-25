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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.learn.smartabsensi.features.presentation.view_models.LoginUiState
import com.learn.smartabsensi.features.presentation.view_models.LoginViewModel

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    lvm: LoginViewModel,
    onHomePageClick: () -> Unit,
    onRegistPageClick: () -> Unit
) {
    val loginUiState by lvm.loginUiState.collectAsStateWithLifecycle()

    val email: String by lvm.email.collectAsStateWithLifecycle()
    var errorEmail by remember { mutableStateOf(false) }

    val password: String by lvm.password.collectAsStateWithLifecycle()
    var errorPassword by remember { mutableStateOf(false) }

    val errMessage = if (loginUiState is LoginUiState.Error) {
        (loginUiState as LoginUiState.Error).message
    } else if (errorEmail) {
        "Email is required"
    } else if (errorPassword) {
        "Password is required"
    } else {
        ""
    }

    LaunchedEffect(key1 = loginUiState) {
        if (loginUiState is LoginUiState.Success) {
            onHomePageClick()
        }
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            icon = R.drawable.ic_email,
            nameField = "Email",
            value = email,
            defaultInnerTextField = "smartabsen@gmail.com",
            isNotMeetRequired = errorEmail,
            isUnavailable = loginUiState is LoginUiState.Error,
            keyboardType = KeyboardType.Email
        ) {
            lvm.onEmailChanged(it)
            errorEmail = it.isEmpty()
        }
        PasswordField(
            nameField = "Password",
            value = password,
            defaultInnerTextField = "Masukkan password anda",
            isNotMeetRequirement = errorPassword,
            isNotAvailable = loginUiState is LoginUiState.Error,
            forgetPassword = true
        ) {
            lvm.onPasswordChanged(it)
            errorPassword = it.isEmpty()
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
        Button(
            onClick = {
                if (email.isEmpty()) {
                    errorEmail = true
                } else if (password.isEmpty()) {
                    errorPassword = true
                } else {
                    lvm.loadUser()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
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
                if (loginUiState is LoginUiState.Loading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 4.dp
                    )
                } else {
                    Text(
                        text =  "Masuk Sekarang",
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
        Spacer(Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Belum punya akun?",
                color = TextPrimary,
                fontSize = 14.sp
            )
            Text(
                text = "Masuk disini",
                color = Indigo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onRegistPageClick()
                }
            )
        }
    }
}