package com.learn.smartabsensi.features.presentation.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel

@Composable
fun LogOut(
    modifier: Modifier = Modifier,
    pvm: ProfileViewModel,
    showBox: MutableState<Boolean>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(
                width = 0.dp,
                color = Err.copy(alpha = 0.5f),
                shape = RoundedCornerShape(18.dp)
            )
            .background(
                color = Err.copy(alpha = 0.1f),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                showBox.value = true
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = "Logout",
            tint = Err,
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Err.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Keluar",
            color = Err,
            fontWeight = FontWeight.Bold
        )
    }
}