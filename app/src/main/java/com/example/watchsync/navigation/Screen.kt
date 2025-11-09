package com.example.watchsync.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Onboarding : Screen("onboarding_screen")
    object Main : Screen("main_screen")
}
