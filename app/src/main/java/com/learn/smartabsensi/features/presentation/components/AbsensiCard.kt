package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Primary
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.backgroundIcon

@Composable
fun AbsensiCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.2f),
                spotColor = Color.Black.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://i.pinimg.com/1200x/8a/f2/cf/8af2cf5a97f7275525784d8e34abe186.jpg",
                contentDescription = "BackGround",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier = Modifier.fillMaxWidth(0.8f).padding(18.dp)
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .background(color = Color.White.copy(alpha = 0.6f), shape = RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .padding(6.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "SMA Muhammadiyah 3 Jember",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_point),
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(10.dp)
                    )
                    Text(
                        text = "Dalam jangkauan absensi",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Primary
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
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