package com.learn.smartabsensi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.learn.smartabsensi.core.themes.SmartAbsensiTheme
import com.learn.smartabsensi.features.presentation.components.BottomNav
import com.learn.smartabsensi.features.presentation.view_models.CanteenViewModel
import com.learn.smartabsensi.features.presentation.view_models.FoodViewModel
import com.learn.smartabsensi.features.presentation.view_models.HistoryViewModel
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import com.learn.smartabsensi.features.presentation.view_models.NewsViewModel
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel
import com.learn.smartabsensi.features.presentation.view_models.UserViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val engine = rememberNavHostEngine()
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val isCurrentDestination = currentBackStackEntry?.destination
//            val selected = BottomBarDestination.entries.map {
//               isCurrentDestination == it.destination
//            }
            SmartAbsensiTheme {
                Scaffold(
                    bottomBar = {
                        BottomNav(
                            navController = navController
                        )
                    }
                ) { padding ->
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        engine = engine,
                        modifier = Modifier.Companion.padding(top = 40.dp),
                        dependenciesContainerBuilder = {
                            dependency(viewModel<HomeViewModel>())
                            dependency(viewModel<HistoryViewModel>())
                            dependency(viewModel<CanteenViewModel>())
                            dependency(viewModel<ProfileViewModel>())
                            dependency(viewModel<NewsViewModel>())
                            dependency(viewModel<FoodViewModel>())
                            dependency(viewModel<UserViewModel>())
                        }
                    )
                }
            }
        }
    }
}