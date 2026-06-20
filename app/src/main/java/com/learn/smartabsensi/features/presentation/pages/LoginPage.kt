package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.presentation.view_models.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun LoginPage(
    navigator: DestinationsNavigator,
    lvm: LoginViewModel
) {
    Box(modifier = Modifier) {
        AsyncImage(
            model = "https://i.pinimg.com/736x/7b/d0/4c/7bd04c9bb55e0751b329d6ca43010637.jpg",
            contentDescription = "background",
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .shadow(
                    elevation = 40.dp,
                    clip = false,
                    ambientColor = Color.White,
                    spotColor = Color.White
                )
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Masuk ke Akun",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Masuk ke Akun",
                fontSize = 14.sp,
                color = TextSecondary
            )

        }
    }
}