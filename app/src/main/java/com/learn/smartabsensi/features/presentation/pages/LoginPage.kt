package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.learn.smartabsensi.core.themes.IndigoSoft
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.presentation.components.LoginForm
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
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://i.pinimg.com/1200x/3c/64/01/3c64010a9347ae510af5974406723363.jpg",
            contentDescription = "background",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.Center)
                .offset(y = -100.dp)
                .clip(
                    RoundedCornerShape(
                        topStartPercent = 100,
                        topEndPercent = 100
                    )
                )
                .background(IndigoSoft)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 415.dp)
                .fillMaxHeight(1f)
                .background(IndigoSoft)
                .padding(horizontal = 26.dp)
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Masuk ke Akun",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Gunakan Email dan Password yang telah diberikan oleh admin",
                fontSize = 14.sp,
                color = TextSecondary
            )
            Spacer(Modifier.height(20.dp))
            LoginForm(
                navigator = navigator,
                lvm = lvm
            )
        }
    }
}