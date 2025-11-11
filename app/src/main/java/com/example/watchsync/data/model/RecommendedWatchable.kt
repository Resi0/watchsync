package com.example.watchsync.data.model

/**
 * Önerilen film/dizi için uyumluluk yüzdesi ile birlikte Watchable nesnesi
 */
data class RecommendedWatchable(
    val watchable: Watchable,
    val compatibilityPercentage: Int // 0-100 arası uyumluluk yüzdesi
)


