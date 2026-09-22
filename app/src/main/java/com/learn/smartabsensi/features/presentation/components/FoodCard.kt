package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil3.compose.AsyncImage
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.DarkIndigo
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.data.models.FoodModel
import com.learn.smartabsensi.features.presentation.view_models.CanteenViewModel
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun FoodCard(
    modifier: Modifier = Modifier,
    foodModel: FoodModel,
    vm: ViewModel
) {

    Column(
        modifier = modifier
            .width(170.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false,
                ambientColor = DarkIndigo,
                spotColor = DarkIndigo.copy(alpha = 0.3f)
            )
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(4.dp)
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
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 6.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rp ${
                    NumberFormat.getNumberInstance(Locale("id", "ID"))
                        .format(foodModel.price)
                }",
                color = Indigo,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        clip = false,
                        ambientColor = Indigo,
                        spotColor = Indigo.copy(alpha = 0.6f)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .background(Indigo)
                    .padding(horizontal = 6.dp)
                    .clickable {
                        when (vm) {
                            is CanteenViewModel -> {
                                vm.updateFavoriteFood(foodModel)
                            }
                            is HomeViewModel -> {
                                vm.updateFavoriteFood(foodModel)
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.love),
                    contentDescription = "favorite",
                    tint = Color.White,
                    modifier = Modifier.size(15.dp).padding(vertical = 4.dp)
                )
            }
        }
    }
}