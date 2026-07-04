package com.learn.smartabsensi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.learn.smartabsensi.core.themes.SmartAbsensiTheme
import com.learn.smartabsensi.core.utils.BottomBarDestination
import com.learn.smartabsensi.features.presentation.components.BottomNav
import com.learn.smartabsensi.features.presentation.view_models.CanteenViewModel
import com.learn.smartabsensi.features.presentation.view_models.HistoryViewModel
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import com.learn.smartabsensi.features.presentation.view_models.LoginViewModel
import com.learn.smartabsensi.features.presentation.view_models.ProfileViewModel
import com.learn.smartabsensi.features.presentation.view_models.RegistViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.HomePageDestination
import com.ramcosta.composedestinations.generated.destinations.RegistPageDestination
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val isReady = mutableStateOf(false)
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            !isReady.value
        }

        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        lifecycleScope.launch {
            delay(3000)
            isReady.value = true
        }

        val currentUser = auth.currentUser
        val startRoute =
            if (currentUser != null) {
                HomePageDestination
            } else {
                RegistPageDestination
            }

        enableEdgeToEdge()
        setContent {
            val engine = rememberNavHostEngine()
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val isCurrentDestination = currentBackStackEntry?.destination?.route

            SmartAbsensiTheme {
                Scaffold(
                    bottomBar = {
                        BottomBarDestination.entries.forEach {
                            if (it.destination.route == isCurrentDestination) {
                                BottomNav(
                                    navController = navController
                                )
                            }
                        }
                    }
                ) { padding ->
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        engine = engine,
                        modifier = Modifier.Companion.padding(top = 40.dp),
                        start = startRoute,
                        dependenciesContainerBuilder = {
                            dependency(hiltViewModel<HomeViewModel>())
                            dependency(hiltViewModel<HistoryViewModel>())
                            dependency(hiltViewModel<CanteenViewModel>())
                            dependency(hiltViewModel<ProfileViewModel>())
                            dependency(hiltViewModel<LoginViewModel>())
                            dependency(hiltViewModel<RegistViewModel>())
                        }
                    )
                }
            }
        }
    }
}