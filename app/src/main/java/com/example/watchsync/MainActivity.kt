package com.example.watchsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.watchsync.navigation.WatchSyncNavGraph
import com.example.watchsync.ui.theme.WatchSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WatchSyncTheme(darkTheme = true) {
                Box(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    WatchSyncNavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}