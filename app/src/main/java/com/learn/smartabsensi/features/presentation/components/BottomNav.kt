package com.learn.smartabsensi.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.utils.BottomNavItem
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.ramcosta.composedestinations.utils.isRouteOnBackStackAsState

@Composable
fun BottomNav(
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier
) {
    val currentRoute = backStack.lastOrNull()

    Row(
        modifier = Modifier
            .padding(bottom = 30.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.Center
    ) {
        BottomNavItem.entries.forEach { item ->

            val selected = currentRoute == item.route

            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(16.dp),
                        clip = false,
                        ambientColor = Indigo,
                        spotColor = Indigo.copy(alpha = 0.4f)
                    )
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .selectable(
                        selected = selected,
                        onClick = {
                            if (currentRoute != item.route) {
                                backStack.clear()
                                backStack.add(item.route)
                            }
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.label,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(vertical = 8.dp, horizontal = 10.dp),
                    tint = if (selected) Indigo else Color.Gray.copy(alpha = 0.7f)
                )
            }
        }
    }
}