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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.core.themes.TextPrimary

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    icon: Int,
    nameField: String,
    value: String,
    defaultInnerTextField: String,
    isUnavailable: Boolean = false,
    isNotMeetRequired: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val isNotMeetRequirement = isNotMeetRequired
    ConstraintLayout(modifier = modifier) {
        val (
            textFieldName,
            textField
        ) = createRefs()
        Text(
            text = nameField,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(textFieldName) {
                top.linkTo(parent.top)
                start.linkTo(textField.start)
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
                    else if (isUnavailable) 4.dp
                    else 0.dp,

                    color = if (isFocused) Indigo.copy(alpha = 0.2f)
                    else if (isNotMeetRequirement) Err.copy(alpha = 0.2f)
                    else if (isUnavailable) Err.copy(alpha = 0.2f)
                    else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = if (isFocused) 2.dp
                    else if (isNotMeetRequirement) 1.dp
                    else if (isUnavailable) 1.dp
                    else 0.dp,

                    color = if (isFocused) Indigo
                    else if (isNotMeetRequirement) Err
                    else if (isUnavailable) Err
                    else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(
                    top = 15.dp,
                    bottom = 15.dp,
                    start = 12.dp,
                    end = 12.dp
                )
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .constrainAs(textField) {
                    top.linkTo(textFieldName.bottom)
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
                        painter = painterResource(id = icon),
                        contentDescription = "NISN",
                        tint = if (isFocused) Indigo else Color.Gray.copy(alpha = 0.7f)
                    )
                    Spacer(Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
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
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}