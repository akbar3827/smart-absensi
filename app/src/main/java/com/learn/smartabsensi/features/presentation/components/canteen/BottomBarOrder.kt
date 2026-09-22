package com.learn.smartabsensi.features.presentation.components.canteen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.data.models.FoodModel
import com.learn.smartabsensi.features.presentation.view_models.CanteenViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun BottomBarOrder(
    modifier: Modifier = Modifier,
    cvm: CanteenViewModel,
    orderQuantity: Int,
    foodItem: FoodModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(color = Indigo, shape = CircleShape)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = foodItem.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = foodItem.description,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp
                    )
                }
                Text(
                    text = NumberFormat.getNumberInstance(Locale("id", "ID"))
                        .format(foodItem.price * orderQuantity),
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Column(
                modifier = Modifier
                    .width(25.dp)
                    .height(60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                            cvm.orderQuantityChanged(orderQuantity + 1)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = orderQuantity.toString(),
                    fontSize = 14.sp,
                    color = TextPrimary,
                    modifier = Modifier
                        .background(Color.White)
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
                            if (orderQuantity > 1) {
                                cvm.orderQuantityChanged(orderQuantity - 1)
                            }
                            if (orderQuantity <= 1) {
                                cvm.bottomBarShowedChanged(false)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "-",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            IconButton(
                modifier = Modifier
                    .size(60.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(26.dp),
                        clip = false,
                        ambientColor = Indigo,
                        spotColor = Indigo.copy(alpha = 0.5f)
                    )
                    .border(
                        width = 1.5.dp,
                        color = Indigo,
                        shape = RoundedCornerShape(26.dp)
                    )
                    .background(Color.White, shape = RoundedCornerShape(26.dp)),
                onClick = {
                    cvm.bottomBarShowedChanged(false)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = "basket",
                    tint = Indigo,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }
    }
}