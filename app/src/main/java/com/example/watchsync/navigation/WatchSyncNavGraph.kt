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
import org.json.JSONObject
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
                    // Ratings'i JSON string'e çevir ve URL encode et
                    val ratingsJson = JSONObject(ratings).toString()
                    val encodedRatings = URLEncoder.encode(ratingsJson, StandardCharsets.UTF_8.toString())
                    
                    // Profil oluşturma tamamlandı, ana sayfaya git
                    // Ratings'i navigation argument olarak gönder
                    navController.navigate("${Screen.Main.route}?ratings=$encodedRatings") {
                        // Onboarding ekranını geri yığınından kaldır
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = "${Screen.Main.route}?ratings={ratings}",
            arguments = listOf(
                navArgument("ratings") {
                    type = NavType.StringType
                    defaultValue = "{}"
                }
            )
        ) { backStackEntry ->
            // Navigation argument'tan (savedStateHandle'dan) ratings'i al
            // Navigation Compose otomatik olarak URL decode yapar
            val ratingsJson = backStackEntry.arguments?.getString("ratings") ?: "{}"
            val ratings = parseRatingsFromJson(ratingsJson)
            
            MainScreen(
                ratings = ratings,
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

/**
 * JSON string'den Map<String, Int> ratings'e çevirir
 */
private fun parseRatingsFromJson(jsonString: String): Map<String, Int> {
    return try {
        val jsonObject = JSONObject(jsonString)
        val ratingsMap = mutableMapOf<String, Int>()
        jsonObject.keys().forEach { key ->
            ratingsMap[key] = jsonObject.getInt(key)
        }
        ratingsMap
    } catch (e: Exception) {
        emptyMap()
    }
}
