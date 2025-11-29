package com.example.watchsync.data.model

/**
 * Keşfet ekranında gösterilecek bir sohbet odasını temsil eder.
 */
data class ChatRoom(
    val id: String,
    val title: String,
    val description: String,
    val type: RoomType,
    val watchable: Watchable? = null,
    val episodeInfo: String? = null,
    val participantCount: Int,
    val isPrivate: Boolean = false
)
