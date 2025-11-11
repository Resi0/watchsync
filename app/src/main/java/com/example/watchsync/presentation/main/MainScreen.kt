package com.example.watchsync.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.watchsync.navigation.BottomNavItem
import com.example.watchsync.presentation.chat.ChatListScreen
import com.example.watchsync.presentation.home.HomeScreen
import com.example.watchsync.presentation.matches.MatchesScreen
import com.example.watchsync.presentation.profile.ProfileScreen
import com.example.watchsync.ui.theme.ElectricBlue
import com.example.watchsync.ui.theme.Turquoise

/**
 * Ana ekran komponenti. Scaffold yapısı ile alt navigasyon barını içerir.
 * Bu ekran, uygulamanın ana iskeleti olarak görev yapar ve
 * Ana Sayfa, Eşleşmeler ve Profil ekranları arasında geçişi yönetir.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    ratings: Map<String, Int> = emptyMap(),
    onNavigateToProfile: (String) -> Unit = {}
) {
    // Mevcut navigation state'i al
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    Scaffold(
        // Scaffold'un arka plan rengi
        containerColor = Color(0xFF0A0E27),
        // Üst app bar
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "WatchSync",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F1419),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate("chat_list") {
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = "Sohbetler",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        // Alt navigasyon barı
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0F1419),
                contentColor = Color.White
            ) {
                // BottomNavItem'daki tüm öğeleri döngü ile ekle
                BottomNavItem.items.forEach { item ->
                    // Mevcut ekranın seçili olup olmadığını kontrol et
                    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(text = item.title)
                        },
                        selected = selected,
                        onClick = {
                            // Kullanıcı bir öğeye tıkladığında ilgili ekrana geçiş yap
                            navController.navigate(item.route) {
                                // Alt navigasyonda her ekranın kendi yığını olmalı
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                // Aynı ekrana tekrar tıklanırsa state'i koru
                                launchSingleTop = true
                                // State'i geri yüklemek için
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Turquoise,
                            selectedTextColor = Turquoise,
                            unselectedIconColor = Color.White.copy(alpha = 0.6f),
                            unselectedTextColor = Color.White.copy(alpha = 0.6f),
                            indicatorColor = ElectricBlue.copy(alpha = 0.2f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        // İç içe NavHost - Ana Sayfa, Eşleşmeler ve Profil ekranları arasında geçişi yönetir
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Ana Sayfa ekranı
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    ratings = ratings,
                    onNavigateToProfile = onNavigateToProfile
                )
            }
            // Eşleşmeler ekranı
            composable(BottomNavItem.Matches.route) {
                MatchesScreen(
                    onNavigateToProfile = onNavigateToProfile
                )
            }
            // Profil ekranı
            composable(BottomNavItem.Profile.route) {
                ProfileScreen()
            }
            // Sohbet listesi ekranı
            composable("chat_list") {
                ChatListScreen(
                    onChatClick = { userId ->
                        // TODO: Birebir sohbet ekranına git
                    }
                )
            }
        }
    }
}
