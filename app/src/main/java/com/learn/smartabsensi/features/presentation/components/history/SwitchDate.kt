package com.learn.smartabsensi.features.presentation.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.presentation.view_models.HistoryViewModel
import java.time.LocalDate

@Composable
fun SwitchDate(
    modifier: Modifier = Modifier,
    hvm: HistoryViewModel,
    date: LocalDate,
    dateResult: String,
    currentDate: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 3.dp,
                clip = false,
                shape = CircleShape,
                spotColor = Color.Gray.copy(alpha = 0.5f),
                ambientColor = Color.DarkGray.copy(alpha = 0.7f)
            )
            .clip(CircleShape)
            .background(color = Color.White, shape = CircleShape)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                hvm.onDataChanged(date.minusMonths(1))
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                tint = TextPrimary,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = Background)
                    .padding(8.dp)
            )
        }
        Text(
            text = dateResult,
            color = TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = {
                if (dateResult != currentDate) {
                    hvm.onDataChanged(date.plusMonths(1))
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                tint = TextPrimary,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = Background)
                    .padding(8.dp)
            )
        }
    }
}