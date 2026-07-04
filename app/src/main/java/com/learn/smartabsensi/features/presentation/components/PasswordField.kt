package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.core.themes.TextPrimary

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    value: String,
    nameField: String,
    defaultInnerTextField: String,
    isNotMeetRequirement: Boolean,
    isNotAvailable: Boolean = false,
    forgetPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = modifier
    ) {
        val (
            passwordName,
            password,
            forgetPw
        ) = createRefs()

        Text(
            text = nameField,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(passwordName) {
                top.linkTo(parent.top)
                start.linkTo(password.start)
            }
        )
        BasicTextField(
            value = value,
            singleLine = true,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = false,
                    ambientColor = IndigoLigth,
                    spotColor = IndigoLigth.copy(alpha = 0.3f)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(
                    width = if (isFocused) 4.dp
                    else if (isNotMeetRequirement) 4.dp
                    else if (isNotAvailable) 4.dp
                    else 0.dp,

                    color = if (isFocused) Indigo.copy(alpha = 0.3f)
                    else if (isNotMeetRequirement) Err.copy(alpha = 0.3f)
                    else if (isNotAvailable) Err.copy(alpha = 0.3f)
                    else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = if (isFocused) 2.dp
                    else if (isNotMeetRequirement) 1.dp
                    else if (isNotAvailable) 1.dp
                    else 0.dp,

                    color = if (isFocused) Indigo
                    else if (isNotMeetRequirement) Err
                    else if (isNotAvailable) Err
                    else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(
                    top = 6.dp,
                    bottom = 6.dp,
                    start = 12.dp,
                    end = 8.dp
                )
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .constrainAs(password) {
                    top.linkTo(passwordName.bottom, margin = 8.dp)
                },
            cursorBrush = SolidColor(Color.Black.copy(alpha = 0.5f)),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = TextPrimary
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_passwordd),
                        contentDescription = "NISN",
                        tint = if (isFocused) Indigo
                        else Color.Gray.copy(alpha = 0.7f)
                    )
                    Spacer(Modifier.width(8.dp))
                    Box(Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = defaultInnerTextField,
                                color = Color.Gray.copy(alpha = 0.7f),
                                fontSize = 16.sp
                            )
                        } else {
                            innerTextField()
                        }
                    }
                    IconButton(
                        onClick = { isVisible = !isVisible }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isVisible) R.drawable.ic_eye_invisible
                                else R.drawable.ic_eye_visible
                            ),
                            contentDescription = null,
                            tint = if (isFocused) Indigo
                            else Color.Gray.copy(alpha = 0.7f)
                        )
                    }

                }
            },
            visualTransformation = if (isVisible) PasswordVisualTransformation()
            else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (forgetPassword) {
            Text(
                text = "Lupa password?",
                color = Indigo,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(forgetPw) {
                    top.linkTo(password.bottom, margin = 8.dp)
                    end.linkTo(password.end)
                }
            )
        }
    }
}