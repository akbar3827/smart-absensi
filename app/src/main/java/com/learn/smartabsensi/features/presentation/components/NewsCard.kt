package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.learn.smartabsensi.core.themes.BlueWithe
import com.learn.smartabsensi.core.themes.Green
import com.learn.smartabsensi.core.themes.GreenDark
import com.learn.smartabsensi.core.themes.Primary
import com.learn.smartabsensi.core.themes.PrimaryDark
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    description: String,
    image: String,
    category: String
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .width(400.dp)
            .height(220.dp)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false,
                ambientColor = Color.DarkGray,
                spotColor = Color.DarkGray.copy(alpha = 0.5f)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(color = Color.White)
        ) {
            AsyncImage(
                model = image,
                contentDescription = id,
                contentScale = ContentScale.Crop,
                modifier = modifier.matchParentSize()
            )
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = RoundedCornerShape(24.dp),
                        clip = false,
                        ambientColor = Color.DarkGray,
                        spotColor = Color.DarkGray.copy(alpha = 0.8f)
                    )
                    .background(
                        color = if (category.lowercase() == "info") {
                            BlueWithe
                        } else if (category.lowercase() == "event") {
                            Green
                        } else {
                            Color.White
                        }, shape = RoundedCornerShape(20.dp)
                    ).padding(horizontal = 10.dp, vertical = 3.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = category.uppercase(),
                    color = if (category.lowercase() == "info") {
                        PrimaryDark.copy(alpha = 0.6f)
                    } else if ( category.lowercase() == "event") {
                        GreenDark
                    } else {
                        Color.DarkGray
                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(color = Color.White)
                .padding(12.dp)
        ) {
            Text(
                text = title,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = description,
                color = TextSecondary,
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}