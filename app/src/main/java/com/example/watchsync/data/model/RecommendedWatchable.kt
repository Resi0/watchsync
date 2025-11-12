package com.example.watchsync.data.model

/**
 * Ana sayfadaki öne çıkan içerik bölümünde gösterilecek bir öneriyi temsil eder.
 */
data class RecommendedWatchable(
    val watchable: Watchable,
    val reason: String // Örn: "Popüler olduğu için", "Arkadaşın beğendiği için"
)
