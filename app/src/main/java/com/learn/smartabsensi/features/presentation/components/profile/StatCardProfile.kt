package com.learn.smartabsensi.features.presentation.components.profile

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.StatAttendanceProfileModel

@Composable
fun StartCardProfile(
    modifier: Modifier = Modifier,
    percentage: Int,
    listAttendance: List<StatAttendanceProfileModel>
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(18.dp))
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(18.dp),
                clip = false,
                ambientColor = Color.Gray,
                spotColor = Color.Gray.copy(alpha = 0.4f)
            )
            .border(
                width = 0.dp,
                color = Color.Gray.copy(alpha = 0.1f),
                shape = RoundedCornerShape(18.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Kehadiran bulan ini",
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "$percentage%",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                color = Indigo
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape)
                .background(
                    color = Indigo.copy(alpha = 0.1f),
                    shape = CircleShape
                )
        ) {
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth((percentage / 100).toFloat())
                    .background(
                        color = Indigo,
                        shape = CircleShape
                    )
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listAttendance.forEachIndexed { index, stat ->
                Column(
                    modifier = modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedContent(
                        targetState = stat.stat,
                        transitionSpec = {
                            if (targetState > initialState) {
                                (fadeIn() + slideInVertically { it }) togetherWith
                                        (slideOutVertically { -it } + fadeOut())
                            } else {
                                (fadeIn() + slideInVertically { -it }) togetherWith
                                        (slideOutVertically { it } + fadeOut())
                            }
                        },
                        label = ""
                    ) {
                        Text(
                            text = it.toString(),
                            color = stat.color,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                    Text(
                        text = stat.category,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
                if (index < listAttendance.size - 1) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxHeight()
                            .width(0.8.dp)
                            .background(Color.Gray.copy(alpha = 0.2f))
                    )
                }
            }
        }
    }
}