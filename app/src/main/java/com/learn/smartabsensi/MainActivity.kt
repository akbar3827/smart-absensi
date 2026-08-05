package com.learn.smartabsensi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import com.google.firebase.auth.FirebaseAuth
import com.learn.smartabsensi.core.themes.SmartAbsensiTheme
import com.learn.smartabsensi.core.utils.BottomNavItem
import com.learn.smartabsensi.core.utils.NavigationRoot
import com.learn.smartabsensi.core.utils.Route
import com.learn.smartabsensi.features.presentation.components.BottomNav
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val isReady = mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            !isReady.value
        }

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(3000)
            isReady.value = true
        }

        enableEdgeToEdge()
        setContent {
            val auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser
            val startRoute =
                if (currentUser != null) {
                    Route.Home
                } else {
                    Route.Regist
                }

            val backStack = rememberNavBackStack(
                configuration = SavedStateConfiguration {
                    serializersModule = SerializersModule {
                        polymorphic(NavKey::class) {
                            subclass(Route.Regist::class, Route.Regist.serializer())
                            subclass(Route.Login::class, Route.Login.serializer())
                            subclass(Route.Home::class, Route.Home.serializer())
                            subclass(Route.History::class, Route.History.serializer())
                            subclass(Route.Canteen::class, Route.Canteen.serializer())
                            subclass(Route.Profile::class, Route.Profile.serializer())
                            subclass(Route.Notification::class, Route.Notification.serializer())
                            subclass(Route.ChangeProfile::class, Route.ChangeProfile.serializer())
                            subclass(Route.PreferenceNotification::class, Route.PreferenceNotification.serializer())
                            subclass(Route.ChangePassword::class, Route.ChangePassword.serializer())
                            subclass(Route.AmountOfNews::class, Route.AmountOfNews.serializer())
                            subclass(Route.News::class, Route.News.serializer())
                        }
                    }
                },
                startRoute
            )

            val currentRoute = backStack.lastOrNull()

            SmartAbsensiTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavItem.entries.forEach { item ->
                            if (currentRoute == item.route) {
                                BottomNav(
                                    backStack = backStack
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    NavigationRoot(
                        modifier = Modifier.padding(paddingValues),
                        backStack = backStack
                    )
                }
            }
        }
    }
}