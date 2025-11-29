package com.example.watchsync.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = "home",
        title = "Anasayfa",
        icon = Icons.Rounded.Home
    )

    object Explore : BottomNavItem(
        route = "explore",
        title = "Keşfet",
        icon = Icons.Rounded.Explore
    )

    object Matches : BottomNavItem(
        route = "matches",
        title = "Eşleşme",
        icon = Icons.Rounded.Favorite
    )

    object Likes : BottomNavItem(
        route = "likes",
        title = "Beğeniler",
        icon = Icons.Rounded.ThumbUp
    )

    object Profile : BottomNavItem(
        route = "profile",
        title = "Profil",
        icon = Icons.Rounded.Person
    )

    companion object {
        val items = listOf(Home, Explore, Matches, Likes, Profile)
    }
}
