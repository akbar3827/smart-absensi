package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.IndigoLigth
import com.learn.smartabsensi.core.themes.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Selection(
    modifier: Modifier = Modifier,
    fieldName: String,
    opsiPilihan: List<String>,
    selectedOption: String,
    isNotMeetRequirement: Boolean,
    onOptionSelected: (String) -> Unit
) {
    var isNotMeetRequirement = isNotMeetRequirement
    var expanded by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = modifier
    ) {
        val (
            text1,
            selection1
        ) = createRefs()

        Text(
            text = fieldName,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = TextPrimary,
            modifier = Modifier.constrainAs(text1) {
                top.linkTo(parent.top)
                start.linkTo(selection1.start)
            }
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(selection1) {
                    top.linkTo(text1.bottom)
                }
        ) {
            BasicTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = TextPrimary
                ),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier.weight(1f)
                        ) {
                            if (selectedOption.isEmpty() || selectedOption == opsiPilihan[0]) {
                                Text(
                                    text = opsiPilihan[0],
                                    color = TextPrimary
                                )
                            } else {
                                isNotMeetRequirement = false
                                innerTextField()
                            }
                        }
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }
                },
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(16.dp),
                        clip = false,
                        ambientColor = IndigoLigth,
                        spotColor = IndigoLigth.copy(alpha = 0.3f)
                    )
                    .border(
                        width = if (isFocused) 4.dp
                        else if (isNotMeetRequirement) 4.dp
                        else 0.dp,
                        color = if (isFocused) Indigo.copy(alpha = 0.2f)
                        else if (isNotMeetRequirement) Err.copy(alpha = 0.2f)
                        else Color.Transparent,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = if (isFocused) 2.dp
                        else if (isNotMeetRequirement) 1.dp
                        else 0.dp,
                        color = if (isFocused) Indigo
                        else if (isNotMeetRequirement) Err
                        else Color.Transparent,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(
                        horizontal = 10.dp,
                        vertical = 14.dp
                    )
                    .onFocusChanged {
                        isFocused = it.isFocused
                    }
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = Color.White
            ) {
                opsiPilihan.forEach { pilihan ->
                    DropdownMenuItem(
                        text = { Text(text = pilihan, color = TextPrimary) },
                        onClick = {
                            onOptionSelected(pilihan)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}