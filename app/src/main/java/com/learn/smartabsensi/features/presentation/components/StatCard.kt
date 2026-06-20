package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.UserModel

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    stat: String,
    color: Color,
    category: String
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false,
                ambientColor = Color.DarkGray.copy(alpha = 0.4f),
                spotColor = Color.DarkGray.copy(alpha = 0.1f)
            )
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 14.dp)
    ) {
        Text(
            text = category,
            fontSize = 13.sp,
            color = TextSecondary
        )
        Text(
            text = stat,
            color = color,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = "hari ini bulan ini",
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
}