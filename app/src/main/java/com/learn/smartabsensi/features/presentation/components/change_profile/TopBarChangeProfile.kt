package com.learn.smartabsensi.features.presentation.components.change_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.presentation.view_models.UserUpdateUiState
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun TopBarChangeProfile(
    modifier: Modifier = Modifier,
    uiState: UserUpdateUiState,
    previousScreen: () -> Unit,
    onSaveClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 30.dp)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconButton(
                onClick = {
                    previousScreen()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Back",
                    tint = TextPrimary
                )
            }
            Column {
                Text(
                    text = "Ubah Profil",
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "Data profil kamu",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
        Button(
            onClick = {
                onSaveClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Indigo,
                contentColor = Color.White
            ),
            shape = CircleShape
        ) {
            if (uiState is UserUpdateUiState.Loading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = "Simpan",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}