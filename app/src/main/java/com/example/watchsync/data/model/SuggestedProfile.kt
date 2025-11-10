package com.example.watchsync.data.model

/**
 * Eşleşmeler ekranında gösterilecek, önerilen bir profili temsil eden veri sınıfı.
 */
data class SuggestedProfile(
    val user: User,
    val age: Int,
    val compatibilityPercentage: Int,
    val commonWatchables: List<Watchable>
)
