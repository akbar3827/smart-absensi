package com.learn.smartabsensi.features.presentation.components.home

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
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
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.features.data.models.UserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbsensiCard(
    modifier: Modifier = Modifier,
    currentTime: String,
    onBottomSheetChanged: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 14.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false,
                ambientColor = Indigo,
                spotColor = Indigo.copy(alpha = 0.5f)
            ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Indigo)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = Color.White.copy(alpha = 0.2f)
                            )
                            .padding(10.dp),
                        painter = painterResource(id = R.drawable.ic_location),
                        tint = Color.White,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "SMA Muhammadiyah 3 Jember",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(2.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_point),
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Dalam jangkauan absensi",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_point),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(4.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = currentTime,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        onBottomSheetChanged(true)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Indigo
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_grid_4_column),
                            contentDescription = null
                        )
                        Text(
                            text = "Absen Sekarang",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}