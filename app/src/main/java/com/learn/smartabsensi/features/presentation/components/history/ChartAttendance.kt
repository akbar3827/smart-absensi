package com.learn.smartabsensi.features.presentation.components.history

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
            total = attendances.count { it.status == "Hadir" } + 1,
            color = Teal
        ),
        ChartAttendanceModelType(
            title = "Sakit",
            total = attendances.count { it.status == "Sakit" } + 1,
            color = Amber
        ),
        ChartAttendanceModelType(
            title = "Izin",
            total = attendances.count { it.status == "Izin" } + 1,
            color = Orange
        ),
        ChartAttendanceModelType(
            title = "Dispen",
            total = attendances.count { it.status == "Dispen" } + 1,
            color = Purple
        ),
        ChartAttendanceModelType(
            title = "Alfa",
            total = attendances.count { it.status == "Alfa" } + 1,
            color = Err
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
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
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            charts.forEach { item ->
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = (item.total - 1).toString(),
                            color = item.color,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .align(alignment = Alignment.BottomCenter)
                                .padding(
                                    bottom = animateDpAsState(
                                        targetValue = (item.total * 8).dp,
                                        animationSpec = tween(
                                            durationMillis = 300,
                                            delayMillis = 100
                                        )
                                    ).value
                                )
                        )
                        Box(
                            modifier = Modifier
                                .height(
                                    animateDpAsState(
                                        targetValue = (item.total * 5).dp,
                                        animationSpec = tween(
                                            durationMillis = 300,
                                            delayMillis = 100
                                        )
                                    ).value
                                )
                                .fillMaxWidth()
                                .background(
                                    color = item.color,
                                    shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
                                )
                                .align(alignment = Alignment.BottomCenter)
                        )
                    }
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