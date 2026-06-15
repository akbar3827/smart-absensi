package com.learn.smartabsensi

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learn.smartabsensi.ui.theme.Primary
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.ramcosta.composedestinations.utils.isRouteOnBackStackAsState

@Composable
fun BottomNav(navController: NavController) {
    val currentDestination = navController.currentDestinationAsState().value
    NavigationBar(
        modifier = Modifier
            .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
            .height(60.dp)
            .shadow(
                elevation = 5.dp,
                shape = CircleShape,
                clip = false,
                ambientColor = Primary.copy(alpha = 0.8f),
                spotColor = Primary.copy(alpha = 0.5f)
            )
            .clip(shape = CircleShape),
        containerColor = Color.White,
        contentColor = Color.DarkGray,
        windowInsets = WindowInsets(0,0,0,0)
    ) {
        BottomBarDestination.entries.forEach {
            val isCurrentDestOnBackStack = navController.isRouteOnBackStackAsState(it.destination).value

            NavigationBarItem(
                selected = currentDestination == it.destination,
                onClick = {
                    if (isCurrentDestOnBackStack) {
                        navController.popBackStack(
                            route = it.destination.route,
                            inclusive = false
                        )
                        return@NavigationBarItem
                    }
                    navController.navigate(route = it.destination.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier,
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.label,
                        modifier = Modifier.size(28.dp)
                    )},
                alwaysShowLabel = false

            )
        }
    }
}