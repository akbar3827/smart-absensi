package com.learn.smartabsensi.features.presentation.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Purple
import com.learn.smartabsensi.core.themes.Teal
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.core.themes.YellowDark
import com.learn.smartabsensi.features.data.models.SettingUiType
import com.learn.smartabsensi.features.data.models.UserModel

@Composable
fun AccountSetting(
    modifier: Modifier = Modifier,
    user: UserModel,
    onEditProfilePageClick: (UserModel) -> Unit,
    onNotificationPagePageClick: (UserModel) -> Unit,
    onChangePasswordPagePageClick: (UserModel) -> Unit
) {
    val listAccountSet = listOf(
        SettingUiType(
            icon = R.drawable.ic_edit_username,
            name = "Ubah Profil",
            color = Purple,
            description = "Nama, foto, dan info diri",
            onScreenClick = onEditProfilePageClick
        ),
        SettingUiType(
            icon = R.drawable.ic_notification,
            name = "Notifikasi",
            color = YellowDark,
            description = "Atur preferensi notifikasi",
            onScreenClick = onNotificationPagePageClick
        ),
        SettingUiType(
            icon = R.drawable.ic_passwordd,
            name = "Ubah Password",
            color = Teal,
            description = "Terakhir diubah 30 hari lalu",
            onScreenClick = onChangePasswordPagePageClick
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(18.dp),
                clip = false,
                ambientColor = Color.Gray,
                spotColor = Color.Gray.copy(alpha = 0.4f)
            )
            .border(
                width = 0.7.dp,
                color = Color.Gray.copy(alpha = 0.1f),
                shape = RoundedCornerShape(18.dp)
            )
            .background(color = Color.White, shape = RoundedCornerShape(18.dp))
            .padding(horizontal = 16.dp)
    ) {
        listAccountSet.forEachIndexed { index, setting ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .clickable {
                        setting.onScreenClick(user)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = setting.icon),
                        contentDescription = setting.description,
                        tint = setting.color,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                color = setting.color.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(8.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = setting.name,
                            color = TextPrimary,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = setting.description,
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }
                Icon(
                    painter = painterResource(id = setting.arrowRight),
                    contentDescription = "Arrow In",
                    tint = TextSecondary.copy(alpha = 0.5f),
                    modifier = Modifier.size(15.dp)
                )
            }
            if (index < listAccountSet.size - 1) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(Color.Gray.copy(alpha = 0.2f))
                )
            }
        }
    }
}