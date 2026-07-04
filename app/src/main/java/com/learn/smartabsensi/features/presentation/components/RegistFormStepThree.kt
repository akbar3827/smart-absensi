package com.learn.smartabsensi.features.presentation.components

import com.ramcosta.composedestinations.generated.destinations.HomePageDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.foundation.border
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.firebase.auth.FirebaseAuth
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.presentation.view_models.RegistUiState
import com.learn.smartabsensi.features.presentation.view_models.RegistViewModel
import com.ramcosta.composedestinations.generated.destinations.LoginPageDestination
import kotlinx.coroutines.tasks.await

@Composable
fun RegistFormStepThree(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    rvm: RegistViewModel,
    step: MutableState<Int>
) {
    val regexPola = Regex("^(?=.*[A-Z])(?=.*\\d).+$")
    val registUiState by rvm.registUiState.collectAsStateWithLifecycle()

    val password by rvm.password.collectAsStateWithLifecycle()
    var errorPassword by remember { mutableStateOf(false) }
    var errorMessagePassword by remember { mutableStateOf("") }

    val passwordConfirmationn by rvm.passwordConfirmation.collectAsStateWithLifecycle()
    var errorPasswordConfirm by remember { mutableStateOf(false) }
    var errorMessagePasswordConfirm by remember { mutableStateOf("") }



    LaunchedEffect(registUiState) {
        if (registUiState is RegistUiState.Success) {
            navigator.navigate(LoginPageDestination)
        }
    }
    val errMessage = if (registUiState is RegistUiState.Error) {
        (registUiState as RegistUiState.Error).message
    } else if(errorPassword) {
        errorMessagePassword
    } else if (errorPasswordConfirm) {
        errorMessagePasswordConfirm
    } else {
        ""
    }


    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Buat Password",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Password yang kuat menjaga akunmu tetap aman",
            fontSize = 14.sp,
            color = TextSecondary,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        PasswordField(
            nameField = "Password",
            modifier = Modifier.fillMaxWidth(),
            value = password,
            defaultInnerTextField = "Buatlah password yang kuat",
            isNotMeetRequirement = errorPassword
        ) {
            rvm.onPasswordChanged(it)
            errorPassword = it.isEmpty()
        }
        Spacer(Modifier.height(12.dp))
        PasswordField(
            modifier = Modifier.fillMaxWidth(),
            nameField = "Konfirmasi password",
            value = passwordConfirmationn,
            defaultInnerTextField = "Konfirmasi password anda",
            isNotMeetRequirement = errorPasswordConfirm,
        ) {
            rvm.onPasswordConfirmationChanged(it)
            errorPasswordConfirm = it.isEmpty()
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text =  errMessage,
            fontSize = 12.sp,
            color = Err,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(100.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    step.value -= 1
                },
                modifier = Modifier
                    .weight(0.7f)
                    .height(60.dp)
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
                        modifier = Modifier.size(32.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Kembali",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Button(
                onClick = {
                    if (password.isEmpty()) {
                        errorPassword = true
                        errorMessagePassword = "Password is required"

                    } else if (password.length < 8) {
                        errorPassword = true
                        errorMessagePassword = "Invalid password (min. 8 character)"

                    } else if (!password.matches(Regex("^(?=.*[A-Z])(?=.*\\d).+$"))) {
                        errorPassword = true
                        errorMessagePassword = "password must contain at least 1 uppercase letter and 1 number"

                    } else if (passwordConfirmationn.isEmpty()) {
                        errorPasswordConfirm = true
                        errorMessagePasswordConfirm = "Confirm password is required"

                    } else if (password != passwordConfirmationn) {
                        errorPasswordConfirm = true
                        errorMessagePasswordConfirm = "Password is not match"

                    } else {
                        rvm.register()
                    }
                },
                modifier = Modifier
                    .weight(1f)
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
                    if (registUiState is RegistUiState.Loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 4.dp
                        )
                    } else {
                        Text(
                            text = "Regist",
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
            }
        }
    }
}