package com.learn.smartabsensi.features.presentation.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.learn.smartabsensi.core.themes.Primary
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.data.models.FoodModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun FoodCard(
    modifier: Modifier = Modifier,
    foodModel: FoodModel
) {
    Column(
        modifier = modifier
            .height(225.dp)
            .width(170.dp)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false,
                ambientColor = Color.DarkGray,
                spotColor = Color.DarkGray.copy(alpha = 0.5f)
            )
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(8.dp)
    ) {
        AsyncImage(
            model = foodModel.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = foodModel.name,
            color = TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Rp ${
                    NumberFormat.getNumberInstance(Locale("id", "ID"))
                        .format(foodModel.price)
                }",
                color = Primary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = if (foodModel.fav) "+" else "-",
                color = Primary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}