package com.learn.smartabsensi.features.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.AttendanceTypeModel
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel

@Composable
fun KindOfAttendance(
    modifier: Modifier = Modifier,
    hvm: HomeViewModel,
    onFocused: Boolean,
    isNotSelected: MutableState<Boolean>,
    attendance: AttendanceTypeModel,
    selectedOption: String
) {
    Column(
        modifier = modifier
            .height(190.dp)
            .clip(
                RoundedCornerShape(20.dp)
            )
            .padding(6.dp)
            .border(
                width = 1.dp,
                color = if (onFocused) attendance.color
                else if (isNotSelected.value) Err
                else Color.Gray.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                if (onFocused) attendance.color.copy(alpha = 0.1f) else Background,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 14.dp, vertical = 20.dp)
            .selectable(
                selected = attendance.attendance == selectedOption,
                onClick = {
                    hvm.onKindOfAttendanceChanged(attendance.attendance)
                    isNotSelected.value = false
                    hvm.onAttendanceColorChanged(attendance.color)
                },
                role = Role.RadioButton
            )
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = attendance.icon),
                contentDescription = null,
                tint = attendance.color,
                modifier = Modifier.size(40.dp)
            )
            RadioButton(
                selected = attendance.attendance == selectedOption,
                onClick = {
                    null
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = attendance.color,
                    unselectedColor = Color.Gray.copy(alpha = 0.2f)
                )
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = attendance.attendance,
            color = TextPrimary.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = attendance.information,
            color = TextSecondary.copy(alpha = 0.7f),
            fontSize = 15.sp,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            )
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "   ${attendance.requirement}",
            color = if (onFocused) attendance.color
            else if (isNotSelected.value) Err
            else TextSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(
                    if (onFocused) attendance.color.copy(alpha = 0.2f)
                    else if (isNotSelected.value) Err.copy(alpha = 0.1f)
                    else TextSecondary.copy(alpha = 0.1f),
                    shape = CircleShape
                )
        )
    }
}