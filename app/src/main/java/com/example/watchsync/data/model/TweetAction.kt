package com.example.watchsync.data.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Tweet kartındaki bir eylem butonunu temsil eden veri sınıfı.
 */
data class TweetAction(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit = {}
)
