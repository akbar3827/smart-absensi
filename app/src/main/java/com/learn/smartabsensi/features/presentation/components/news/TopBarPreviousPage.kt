package com.learn.smartabsensi.features.presentation.components.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.TextPrimary

@Composable
fun TopBarPreviousPage(
    modifier: Modifier = Modifier,
    onPreviousPage: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 50.dp)
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onPreviousPage()
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            tint = TextPrimary,
            contentDescription = "BackPage"
        )
        Text(
            text = "Kembali",
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
    }
}