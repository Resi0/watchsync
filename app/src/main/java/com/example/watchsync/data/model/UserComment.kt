package com.example.watchsync.data.model

/**
 * Bir kullanıcının bir film/dizi hakkında yaptığı yorumu temsil eder.
 */
data class UserComment(
    val id: String,
    val user: User,
    val watchable: Watchable? = null,
    val comment: String,
    val timestamp: String
)
