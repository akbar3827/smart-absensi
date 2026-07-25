package com.learn.smartabsensi.features.presentation.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Amber
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.Orange
import com.learn.smartabsensi.core.themes.Purple
import com.learn.smartabsensi.core.themes.Teal
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.AttendanceModel
import com.learn.smartabsensi.features.data.models.HintDateAttendanceModelType
import com.learn.smartabsensi.features.presentation.view_models.HistoryViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun Date(
    modifier: Modifier = Modifier,
    hvm: HistoryViewModel,
    year: Int,
    month: Int,
    attendance: List<AttendanceModel>
) {
    val hint = listOf(
        HintDateAttendanceModelType(
            title = "Hadir",
            color = Teal
        ),
        HintDateAttendanceModelType(
            title = "Sakit",
            color = Amber
        ),
        HintDateAttendanceModelType(
            title = "Izin",
            color = Orange
        ),
        HintDateAttendanceModelType(
            title = "Dispen",
            color = Purple
        ),
        HintDateAttendanceModelType(
            title = "Alfa",
            color = Err
        ),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(350.dp)
            .shadow(
                elevation = 4.dp,
                clip = false,
                shape = RoundedCornerShape(18.dp),
                ambientColor = Color.Gray.copy(alpha = 0.8f),
                spotColor = Color.Gray.copy(alpha = 0.4f)
            )
            .background(color = Color.White, shape = RoundedCornerShape(18.dp))
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val days = remember {
            (0..6).map { offset ->
                DayOfWeek.of(DayOfWeek.MONDAY.value + offset)
                    .getDisplayName(TextStyle.SHORT, Locale.getDefault())
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val getWholeDay = hvm.getDaysInMonth(year = year, month = month)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    days.forEach {
                        Text(
                            text = it,
                            color = TextSecondary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            Spacer(Modifier.height(20.dp))
            getWholeDay.chunked(7).forEachIndexed { indexWeek, week ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    week.forEachIndexed { index, day ->
                        val dayy = day.dayOfMonth.toString()
                        val date = day.format(
                            DateTimeFormatter.ofPattern(
                                "yyyy-MM-dd",
                                Locale.ENGLISH
                            )
                        )
                        val attendanceForDate = attendance.find { it.date.contains(date) }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(2.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .background(
                                    color = if (hvm.currentDate == date) Indigo else Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                        ) {
                            Text(
                                text =  dayy,
                                color = if (hvm.currentDate == date) Color.White
                                else if (day.dayOfMonth > week.size && indexWeek == 0) TextSecondary.copy(alpha = 0.5f)
                                else TextPrimary,
                                fontWeight = FontWeight.W900
                            )
                            Spacer(Modifier.height(4.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_point),
                                contentDescription = null,
                                tint = when (attendanceForDate?.status) {
                                    "Hadir" -> Teal
                                    "Sakit" -> Amber
                                    "Izin" -> Orange
                                    "Dispen" -> Purple
                                    "Alfa" -> Err
                                    else -> Color.Transparent
                                },
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }
                    repeat(7 - week.size) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }

        Column(
            Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .height(0.5.dp)
                    .background(color = Color.Gray.copy(alpha = 0.2f))
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                hint.forEach {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_point),
                            contentDescription = null,
                            tint = it.color,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = it.title,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary.copy(alpha = 0.5f),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}