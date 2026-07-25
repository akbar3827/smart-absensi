package com.learn.smartabsensi.features.presentation.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.core.themes.Amber
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Orange
import com.learn.smartabsensi.core.themes.Purple
import com.learn.smartabsensi.core.themes.Teal
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.AttendanceModel
import com.learn.smartabsensi.features.data.models.ChartAttendanceModelType
import com.learn.smartabsensi.features.presentation.view_models.HistoryViewModel

@Composable
fun ChartAttendance(
    modifier: Modifier = Modifier,
    hvm: HistoryViewModel,
    attendances: List<AttendanceModel>
) {
    val charts = listOf(
        ChartAttendanceModelType(
            title = "Hadir",
            total = attendances.map {
                if (it.status == "Hadir") it
            }.size,
            color = Teal
        ),
        ChartAttendanceModelType(
            title = "Sakit",
            total = attendances.map {
                if (it.status == "Sakit") it
            }.size,
            color = Amber
        ),
        ChartAttendanceModelType(
            title = "Izin",
            total = attendances.map {
                if (it.status == "Izin") it
            }.size,
            color = Orange
        ),
        ChartAttendanceModelType(
            title = "Dispen",
            total = attendances.map {
                if (it.status == "Dispen") it
            }.size,
            color = Purple
        ),
        ChartAttendanceModelType(
            title = "Alfa",
            total = attendances.map {
                if (it.status == "Alfa") it else 0
            }.size,
            color = Err
        )
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                clip = false,
                shape = RoundedCornerShape(18.dp),
                ambientColor = Color.Gray.copy(alpha = 0.8f),
                spotColor = Color.Gray.copy(alpha = 0.4f)
            )
            .background(color = Color.White, shape = RoundedCornerShape(18.dp))
            .padding(14.dp)
    ) {
        Text(
            text = "Ringkasan bulan ini",
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            charts.forEach { item ->
                Column(
                    modifier = Modifier.height(200.dp).weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.total.toString(),
                        color = item.color,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight((item.total/10).toFloat())
                            .fillMaxWidth()
                            .background(
                            color = item.color,
                            shape = RoundedCornerShape(8.dp)
                        )
                    )
                    Text(
                        text = item.title,
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}