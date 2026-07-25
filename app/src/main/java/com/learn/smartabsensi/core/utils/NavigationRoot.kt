package com.learn.smartabsensi.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.learn.smartabsensi.features.presentation.pages.CanteenPage
import com.learn.smartabsensi.features.presentation.pages.HistoryPage
import com.learn.smartabsensi.features.presentation.pages.HomePage
import com.learn.smartabsensi.features.presentation.pages.LoginPage
import com.learn.smartabsensi.features.presentation.pages.ProfilePage
import com.learn.smartabsensi.features.presentation.pages.RegistPage
import com.learn.smartabsensi.features.presentation.pages.sub_pages.ChangePasswordPage
import com.learn.smartabsensi.features.presentation.pages.sub_pages.ChangeProfilePage
import com.learn.smartabsensi.features.presentation.pages.sub_pages.NewsPage
import com.learn.smartabsensi.features.presentation.pages.sub_pages.NotificationPage
import com.learn.smartabsensi.features.presentation.pages.sub_pages.PreferenceNotificationPage

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    backStack: NavBackStack<NavKey>,
) {
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Route.Regist -> {
                    NavEntry(key) {
                        RegistPage(
                            onLoginPageClick = {
                                backStack.add(Route.Login)
                            }
                        )
                    }
                }

                is Route.Login -> {
                    NavEntry(key) {
                        LoginPage(
                            onRegistPageClick = {
                                backStack.add(Route.Regist)
                            },
                            onHomePageClick = {
                                backStack.add(Route.Home)
                            }
                        )
                    }
                }

                is Route.Home -> {
                    NavEntry(key) {
                        HomePage(
                            onNewsPageClick = {
                                backStack.add(Route.News(it))
                            },
                            onNotificationPageClick = {
                                backStack.add(Route.Notification(it))
                            }
                        )
                    }
                }

                is Route.History -> {
                    NavEntry(key) {
                        HistoryPage(
                            onNotificationClick = {
                                backStack.add(Route.Notification(it))
                            }
                        )
                    }
                }

                is Route.Canteen -> {
                    NavEntry(key) {
                        CanteenPage()
                    }
                }

                is Route.Profile -> {
                    NavEntry(key) {
                        ProfilePage(
                            onEditProfilePageClick = {
                                backStack.add(Route.ChangeProfile(it))
                            },
                            onLoginPageClick = {
                                backStack.add(Route.Login)
                            },
                            onNotificationPagePageClick = {
                                backStack.add(Route.Notification(it))
                            },
                            onChangePasswordPagePageClick = {
                                backStack.add(Route.ChangePassword(it))
                            }
                        )
                    }
                }

                is Route.ChangeProfile -> {
                    NavEntry(key) {
                        ChangeProfilePage(
                            user = key.user
                        )
                    }
                }

                is Route.Notification -> {
                    NavEntry(key) {
                        NotificationPage(
                            user = key.user
                        )
                    }
                }

                is Route.PreferenceNotification -> {
                    NavEntry(key) {
                        PreferenceNotificationPage(
                            user = key.user
                        )
                    }
                }

                is Route.ChangePassword -> {
                    NavEntry(key) {
                        ChangePasswordPage(
                            user = key.user
                        )
                    }
                }

                is Route.News -> {
                    NavEntry(key) {
                        NewsPage(
                            news = key.news
                        )
                    }
                }

                else -> error("Unknown NavKey $key")
            }
        }
    )
}