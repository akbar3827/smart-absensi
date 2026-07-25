package com.learn.smartabsensi.features.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.core.themes.TextSecondary
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SummaryAttendance(
    modifier: Modifier = Modifier,
    color: Color,
    kindOfAttendance: String,
    attendanceMethod: String
) {
    var times by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        while (isActive) {
            times = SimpleDateFormat(
                "HH.mm",
                Locale.getDefault()
            ).format(Date())

            delay(1000)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = color.copy(0.1f), shape = RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "RINGKASAN ABSENSI",
            color = color,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Jenis",
                color = TextSecondary,
                fontSize = 14.sp
            )
            Text(
                text = kindOfAttendance,
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = 14.sp
            )
        }
        Box(
            modifier = Modifier.height(0.3.dp).fillMaxWidth().background(color = color, shape = CircleShape)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Metode",
                color = TextSecondary,
                fontSize = 14.sp
            )
            Text(
                text = attendanceMethod,
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = 14.sp
            )
        }
        Box(
            modifier = Modifier.height(0.3.dp).fillMaxWidth().background(color = color, shape = CircleShape)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Waktu",
                color = TextSecondary,
                fontSize = 14.sp
            )
            Text(
                text = "$times WIB",
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = 14.sp
            )
        }
    }
}