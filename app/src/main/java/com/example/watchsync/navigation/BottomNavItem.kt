package com.example.watchsync.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = "home",
        title = "Ana Sayfa",
        icon = Icons.Rounded.Home
    )

    object Matches : BottomNavItem(
        route = "matches",
        title = "Eşleşmeler",
        icon = Icons.Rounded.People
    )

    object Profile : BottomNavItem(
        route = "profile",
        title = "Profil",
        icon = Icons.Rounded.Person
    )

    companion object {
        val items = listOf(Home, Matches, Profile)
    }
}

