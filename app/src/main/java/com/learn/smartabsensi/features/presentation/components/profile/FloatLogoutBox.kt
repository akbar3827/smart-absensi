package com.learn.smartabsensi.features.presentation.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel

@Composable
fun FloatLogoutBox(
    modifier: Modifier = Modifier,
    onLoginPageClick: () -> Unit,
    pvm: ProfileViewModel,
    onShowBoxChanged: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .height(150.dp)
                .clip(
                    RoundedCornerShape(18.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Apakah anda yakin untuk keluar?",
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        onShowBoxChanged(false)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border(
                            width = 0.5.dp,
                            color = Color.Gray.copy(alpha = 0.4f),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "Batal",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = {
                        pvm.logout()
                        onLoginPageClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Indigo
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border(
                            width = 0.5.dp,
                            color = Indigo,
                            shape = CircleShape
                        )
                        .background(
                            color = Indigo,
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "Yakin",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}