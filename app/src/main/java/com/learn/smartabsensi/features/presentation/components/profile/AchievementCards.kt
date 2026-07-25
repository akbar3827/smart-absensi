package com.learn.smartabsensi.features.presentation.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.learn.smartabsensi.core.themes.Amber
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.Teal
import com.learn.smartabsensi.core.themes.TextSecondary

@Composable
fun AchievementCards(
    modifier: Modifier = Modifier,
    percentage: Int
) {
    val icons = listOf(
        if (percentage <= 30) {
            R.drawable.ic_tidak_hadir
        } else if (percentage <= 80) {
            R.drawable.ic_hadir
        } else {
            R.drawable.ic_hadir
        },
        R.drawable.ic_cup,
        R.drawable.ic_target
    )
    val colors = listOf(
        if (percentage <= 30) {
            Err
        } else if (percentage <= 60) {
            Teal
        } else {
            Teal
        },
        Amber,
        Err
    )
    val colorsName = listOf(
        if (percentage <= 30) {
            Color.White
        } else if (percentage <= 60) {
            Color.White
        } else {
            Color.White
        },
        Amber,
        TextSecondary
    )
    val names = listOf(
        if (percentage <= 30) {
            "Siswa tidak aktif"
        } else if (percentage <= 60) {
            "Siswa lumayan aktif"
        } else if (percentage <= 80) {
            "Siswa aktif"
        } else {
            "Siswa aktif"
        },
        "Peringkat 99",
        "$percentage% Hadir"
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(icons.size) {
            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .shadow(
                        elevation = 8.dp,
                        clip = false,
                        shape = RoundedCornerShape(18.dp),
                        ambientColor = TextSecondary,
                        spotColor = TextSecondary.copy(alpha = 0.5f)
                    )
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        color = if (it == 0) Indigo else Color.White,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = icons[it]),
                    contentDescription = null,
                    tint = colors[it],
                    modifier = Modifier.size(40.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = names[it],
                    color = colorsName[it],
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}