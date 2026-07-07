package com.learn.smartabsensi.features.presentation.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.DarkIndigo
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.features.data.models.UserModel

@Composable
fun TopBarProfile(
    modifier: Modifier = Modifier,
    user: UserModel
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(IndigoLigth)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "bell",
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterEnd)
                .offset(x = -30.dp, y = -30.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 0.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(color = Color.White.copy(alpha = 0.1f))
                .padding(12.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Background)
                .align(Alignment.BottomCenter)
        )
        AsyncImage(
            model = user.photoUrl,
            contentDescription = "photo profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .shadow(
                    elevation = 8.dp,
                    clip = false,
                    shape = CircleShape,
                    ambientColor = DarkIndigo,
                    spotColor = DarkIndigo.copy(alpha = 0.8f)
                )
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = Indigo,
                    shape = CircleShape
                )
                .align(Alignment.BottomCenter)
        )
    }
}