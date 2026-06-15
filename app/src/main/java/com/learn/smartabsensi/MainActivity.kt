package com.learn.smartabsensi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.learn.smartabsensi.ui.theme.SmartAbsensiTheme
import com.learn.smartabsensi.viewModel.CanteenViewModel
import com.learn.smartabsensi.viewModel.HistoryViewModel
import com.learn.smartabsensi.viewModel.HomeViewModel
import com.learn.smartabsensi.viewModel.ProfileViewModel
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
                        modifier = Modifier.padding(top = 40.dp),
                        dependenciesContainerBuilder = {
                            dependency(viewModel<HomeViewModel>())
                            dependency(viewModel<HistoryViewModel>())
                            dependency(viewModel<CanteenViewModel>())
                            dependency(viewModel<ProfileViewModel>())
                        }
                    )
                }
            }
        }
    }
}
