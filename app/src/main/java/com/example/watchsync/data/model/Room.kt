package com.example.watchsync.data.model

data class Room(
    val id: String,
    val title: String,
    val creator: User,
    val participantCount: Int,
    val watchable: Watchable? = null, // Konuşulan film/dizi
    val topicDescription: String? = null, // Örn: "Sezon 2 Bölüm 5 Hakkında"
    val isVoiceActive: Boolean = true,
    val listenersCount: Int = 0
)

