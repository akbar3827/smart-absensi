package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.Pink
import com.learn.smartabsensi.core.themes.Primary
import com.learn.smartabsensi.core.themes.TextPrimary

@Composable
fun Gender(
    modifier: Modifier = Modifier,
    onGenderChanged: (String) -> Unit
) {
    val genderOptions = listOf("pria", "perempuan")
    var selected by remember { mutableStateOf(0) }
    SingleChoiceSegmentedButtonRow(
        modifier = modifier.fillMaxWidth(),
        space = -8.dp
    ) {
        genderOptions.forEachIndexed { index, label ->
            SegmentedButton(
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    selected = index
                    onGenderChanged(label)
                },
                selected = index == selected,
                icon = {
                    Icon(
                        painter = if (index == 0) painterResource(id = R.drawable.ic_male)
                        else painterResource(id = R.drawable.ic_female),
                        contentDescription = "Gender",
                        tint = if (index == 0) Primary else Pink
                    )
                },
                border = BorderStroke(
                    width = 0.dp,
                    color = Color.White
                ),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = Indigo,
                    inactiveContainerColor = Color.White,
                    activeContentColor = Color.White,
                    inactiveContentColor = TextPrimary
                ),
                modifier = Modifier.height(50.dp)
            ) {
                Spacer(Modifier.width(8.dp))
                Text(
                    text = label,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}