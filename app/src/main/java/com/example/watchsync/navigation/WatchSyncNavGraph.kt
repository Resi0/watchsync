package com.example.watchsync.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.watchsync.presentation.login.LoginScreen
import com.example.watchsync.presentation.main.MainScreen
import com.example.watchsync.presentation.onboarding.OnboardingScreen
import com.example.watchsync.presentation.profile.OtherUserProfileScreen

@Composable
fun WatchSyncNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                onStartClick = {
                    navController.navigate(Screen.Onboarding.route)
                }
            )
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onComplete = { ratings ->
                    // Profil oluşturma tamamlandı, ana sayfaya git
                    navController.navigate(Screen.Main.route) {
                        // Onboarding ekranını geri yığınından kaldır
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Main.route) {
            MainScreen(
                onNavigateToProfile = { userId ->
                    navController.navigate("user_profile/$userId")
                }
            )
        }
        composable(
            route = "user_profile/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            OtherUserProfileScreen(
                userId = userId,
                onBackClick = {
                    navController.popBackStack()
                },
                onConnectClick = {
                    // TODO: Bağlantı kurma işlemi
                }
            )
        }
    }
}
