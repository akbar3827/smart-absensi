package com.learn.smartabsensi.features.presentation.components.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextSecondary

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    stat: String,
    color: Color,
    category: String
) {
    Column(
        modifier = modifier
            .width(110.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false,
                ambientColor = Indigo.copy(alpha = 0.7f),
                spotColor = Indigo.copy(alpha = 0.3f)
            )
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = category,
            fontSize = 13.sp,
            color = TextSecondary
        )
        if (stat.contains("%")) {
            Text(
                text = stat,
                color = color,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        } else {
            AnimatedContent(
                targetState = stat,
                transitionSpec = {
                    if (targetState > initialState) {
                        (fadeIn() + slideInVertically { it }) togetherWith
                                (slideOutVertically { -it } + fadeOut())
                    } else {
                        (fadeIn() +slideInVertically { -it }) togetherWith
                                (slideOutVertically { it } + fadeOut())
                    }
                },
                label = ""
            ) {
                Text(
                    text = it,
                    color = color,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
        Text(
            text = "bulan ini",
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
}