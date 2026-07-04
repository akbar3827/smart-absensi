package com.learn.smartabsensi.features.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel

@Composable
fun AttendanceNoteInput(
    modifier: Modifier = Modifier,
    hvm: HomeViewModel
) {
    val attendanceNote by hvm.attendanceNote.collectAsStateWithLifecycle()
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        value = attendanceNote,
        singleLine = true,
        onValueChange = {
            hvm.onAttendanceNoteChanged(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false,
                ambientColor = IndigoLigth,
                spotColor = IndigoLigth.copy(alpha = 0.3f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Background)
            .border(
                width = if (isFocused) 4.dp
                else 0.dp,
                color = if (isFocused) Indigo.copy(alpha = 0.2f)
                else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = if (isFocused) 1.dp
                else 1.dp,
                color = if (isFocused) Indigo
                else Color.Gray.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        cursorBrush = SolidColor(Color.Black.copy(alpha = 0.5f)),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = TextPrimary
        ),
        decorationBox = { innerTextField ->
            Spacer(Modifier.width(8.dp))
            Box(modifier = Modifier) {
                if (attendanceNote.isEmpty()) {
                    Text(
                        text = "Jelaskan keperluan dispensasi...",
                        color = Color.Gray.copy(alpha = 0.5f),
                        fontSize = 16.sp
                    )
                } else {
                    innerTextField()
                }
            }
        }
    )
}