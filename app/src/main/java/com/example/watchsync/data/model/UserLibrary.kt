package com.example.watchsync.data.model

data class Season(
    val seasonNumber: Int,
    val episodeCount: Int,
    val episodes: List<Episode>
)

data class Episode(
    val episodeNumber: Int,
    val title: String,
    val duration: Int, // dakika cinsinden
    val isWatched: Boolean = false
)

data class UserLibrary(
    val ratedWatchables: List<Watchable> = emptyList(), // Onboarding'te oylananlar
    val watchlist: List<Watchable> = emptyList(), // İzlemek için kaydedilenler
    val watched: List<WatchedContent> = emptyList() // İzlenenler
)

data class WatchedContent(
    val watchable: Watchable,
    val watchedAt: Long = System.currentTimeMillis(),
    val progress: Float = 1.0f, // 0.0 - 1.0 arası
    val watchedEpisodes: Map<Int, Set<Int>> = emptyMap() // Sezon numarası -> İzlenen bölüm numaraları
)

