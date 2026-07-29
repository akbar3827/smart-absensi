package com.learn.smartabsensi.features.presentation.components.canteen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.presentation.view_models.CanteenViewModel

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    isFocused: MutableState<Boolean>,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(Color.Black.copy(alpha = 0.5f)),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = TextPrimary
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    tint = if (isFocused.value) Indigo else Color.Gray.copy(alpha = 0.7f)
                )
                Spacer(Modifier.width(8.dp))
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Cari makanan atau minuman...",
                            color = Color.Gray.copy(alpha = 0.7f),
                            fontSize = 16.sp
                        )
                    } else {
                        innerTextField()
                    }
                }
            }
        },
        modifier = modifier.fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false,
                ambientColor = IndigoLigth,
                spotColor = IndigoLigth.copy(alpha = 0.3f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(
                width = if (isFocused.value) 4.dp
                else 0.dp,
                color = if (isFocused.value) Indigo.copy(alpha = 0.2f)
                else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            ).border(
                width = if (isFocused.value) 2.dp
                else 0.dp,
                color = if (isFocused.value) Indigo
                else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 12.dp,
                end = 12.dp
            )
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused
            }
    )
}