package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Primary
import com.learn.smartabsensi.core.themes.PrimaryDark
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White.copy(alpha = 0.1f))
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Image(
                painter = painterResource(id = R.drawable.profile_pict),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        ambientColor = PrimaryDark,
                        spotColor = PrimaryDark.copy(alpha = 0.5f)
                    )
                    .padding(3.dp)
                    .clip(CircleShape)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Primary
                        )
                    ) {
                        append("Smart")
                    }
                    append("Absen")
                },
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "bell",
            tint = PrimaryDark,
            modifier = Modifier.padding(2.dp).aspectRatio(1f, matchHeightConstraintsFirst = true)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray.copy(alpha = 0.5f)
                )
                .background(color = Color.White, shape = RoundedCornerShape(12.dp)).padding(10.dp)
        )
    }
}